pipeline {
	agent any  
	stages { 	
		stage('test') {
			agent {
				docker {
					image 'maven:3.6.3-jdk-11-slim' 
					args '-v /opt/.m2:/root/.m2' 
				}
			}
			steps {
				sh 'mvn clean verify serenity:aggregate'
			}               
		}
		
		 stage ('Reports') {

            steps {
                cucumber buildStatus: "UNSTABLE",
                    fileIncludePattern: "**/*.json",
                    jsonReportDirectory: '/var/jenkins_home/workspace/automation_php_travels@2/target/'

            }

        }
		
	}

}