node {
    
    stage('Descargar fuentes') {
        git credentialsId: 'alt.carlos@gmail.com', url: 'https://github.com/ch4rl1/jbgroup-devops-facturacion.git'
    }

    stage('Compilar') {
        bat 'mvn -f facturacion-web/pom.xml clean compile'
    }
    
    stage('Pruebas Unitarias') {
        
        bat 'mvn -f facturacion-web/pom.xml test'
        
        step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/*.xml'])
        
    }
    
    stage('Pruebas de Integración') {
        def SCRIPT_CREACION_BD = "${WORKSPACE}/facturacion-web/src/main/resources/db/create_database_integration_test_mysql.sql"
        def SCRIPT_ELIMINACION_BD = "${WORKSPACE}/facturacion-web/src/main/resources/db/delete_database_integration_test_mysql.sql"
        
        dir("c:\\xampp\\mysql\\bin"){

            bat "mysql --user=test --password=test -e \"source ${SCRIPT_CREACION_BD}\""
            
            bat "mvn -f \"${WORKSPACE}/facturacion-web/pom.xml\" verify -DskipUTs=true"
            
            bat "mysql --user=test --password=test -e \"source ${SCRIPT_ELIMINACION_BD}\""
            
        }
        
        step([$class: 'JUnitResultArchiver', testResults: '**/target/failsafe-reports/*.xml'])
        
    }
    
    stage('Análisis Estático con Sonar') {
        
        withSonarQubeEnv('MiSonarServer') {
            
            bat 'mvn -f facturacion-web/pom.xml sonar:sonar'
            
            /*def qg = waitForQualityGate() 
            if (qg.status != 'OK') {
                error "La aplicación no cumple con los estándares de calidad: ${qg.status}"
            }*/
        
        }
        
    }
    
    // No need to occupy a node
    stage("Quality Gate"){
      timeout(time: 5, unit: 'MINUTES') { // Just in case something goes wrong, pipeline will be killed after a timeout
        def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
        if (qg.status != 'OK') {
          error "${qg.status}: La aplicación no cumple con los estándares de calidad: ${qg.status}"
        }
      }
    }
       
    stage('Empaquetar y Versionar') {
        
        bat 'mvn -f facturacion-web/pom.xml package -DskipTests=true'
        
        def server = Artifactory.server 'MiArtifactory' 

        def uploadSpec = """{ 

          "files": [ 

            { 

              "pattern": "facturacion-web/target/*.jar", 

              "target": "aplicaciones-java/FacturacionWeb/${BUILD_NUMBER}/", 

              "props": "pruebas-unitarias=si;pruebas-integracion=si" 

            } 

          ] 

        }""" 

        server.upload(uploadSpec) 
        
    }
    
    
    
}


