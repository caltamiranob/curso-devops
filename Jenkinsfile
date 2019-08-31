node {

	stage('Descargar Fuentes') {
		echo 'Descargando Fuentes ....' 
		dir('facturacion-web') {
			git '/c/workspace/wsDevOpsFinal/curso-devops-agosto-master'
		}

    }

	stage('Compilar') {
		echo 'Compilando ....'
		bat 'mvn -f facturacion-web/pom.xml clean compile'
	}

	stage('Pruebas Unitarias')  {
		echo 'Ejecutando Pruebas Unitarias ....'
		bat 'mvn -f facturacion-web/pom.xml test'
		junit '**/target/surefire-reports/*.xml'
    }
    
    stage('Pruebas de Integración') {
    	echo 'Ejecutando Pruebas de Integracion'
        bat "mvn -f facturacion-web/pom.xml verify -DskipUTs=true"
        step([$class: 'JUnitResultArchiver', testResults: '**/target/failsafe-reports/*.xml'])
        
    }

	stage('Análisis Estático con Sonar') {
        
        withSonarQubeEnv('MiSonarServer') {
            
            bat 'mvn -f facturacion-web/pom.xml sonar:sonar -Dsonar.projectName=facturacion-web-$BUILD-NUMBER'
            
            //bat 'mvn clean verify sonar:sonar -Dsonar.projectName=example-project -Dsonar.projectKey=example-project -Dsonar.projectVersion=$BUILD_NUMBER'
            
        }
        
    }
    
    //QUALITY GATE
    
    stage('Generar Build') {
		echo 'Generando Build ...'
		bat 'mvn -f facturacion-web/pom.xml package -DskipTests=true'                       
                       
   	}

    
    stage('Versionar') {
        
        def server = Artifactory.server 'MiArtifactory' 

        def uploadSpec = """{ 

          "files": [ 

            { 

              "pattern": "target/*.jar", 

              "target": "aplicaciones-java/facturacion-web-completo/desarrollo/${BUILD_NUMBER}/", 

              "props": "pruebas-unitarias=si;pruebas-integracion=si;analisis-codigo-estatico=si" 

            } 

          ] 

        }""" 

        server.upload(uploadSpec) 
        
    }


	stage('Pruebas Funcionales') {
		echo 'Ejecutando pruebas funcionales ...'
		dir('facturacion-web-auto-ui') {
                  
		    git '/c/workspace/wsDevOpsFinal/facturacion-web-automatizacion-ui'
            bat 'mvn test'
            //publicar reporte testNG
            step([$class: 'Publisher'])
		}
	                   
    }
    
    stage('Pruebas de Rendimiento') {

	    echo 'Ejecutando pruebas de rendimiento ...'

	    withEnv(['JMETER_HOME=C:\\herramientas\\apache-jmeter-4.0']) {

	    	bat "C:\\herramientas\\apache-jmeter-4.0\\bin\\jmeter.bat -Jjmeter.save.saveservice.output_format=xml  -n -t src/main/resources/jmeter/plan-pruebas-facturacion-web.jmx -l src/main/resources/jmeter/plan-pruebas-facturacion-web.jtl"

	    	perfReport errorFailedThreshold: 0, errorUnstableThreshold: 0, percentiles: '0,50,90,100', relativeFailedThresholdNegative: 0.0, relativeFailedThresholdPositive: 0.0, relativeUnstableThresholdNegative: 0.0, relativeUnstableThresholdPositive: 0.0, sourceDataFiles: 'src/main/resources/jmeter/plan-pruebas-facturacion-web.jtl'                      

	    }

    }



}





