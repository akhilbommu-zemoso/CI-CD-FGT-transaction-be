import groovy.transform.Field

podTemplate(label: 'fgt-transaction-be', containers: [
	containerTemplate(name: 'fgt-docker', image: 'docker:19.03', command: 'cat', ttyEnabled: true),
	containerTemplate(name: 'fgt-java', image: 'maven:3.8.1-adoptopenjdk-11', command: 'cat', ttyEnabled: true) ],
	volumes: [hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock')]
){
	node('fgt-transaction-be'){
		environment {
			//VERSION = "${env.GIT_COMMIT}"
			DOCKERHUB_CREDENTIALS= credentials('akhil-dockerhub')
		}
		stage('Build Jar'){
			git 'https://github.com/akhilbommu-zemoso/CI-CD-FGT-transaction-be.git'
			container('fgt-java'){
				sh 'mvn -B -DskipTests clean package'
			}    
		}
		stage('Build Docker'){
			container('fgt-docker'){
				sh 'docker build -t akhilzemoso/be_transaction_jenkins:latest .'
				sh 'docker images'
			}
		}  
		stage('Push Docker'){
			container('fgt-docker'){
				sh 'ls'
				withCredentials([usernamePassword(credentialsId: 'akhil-dockerhub', usernameVariable: 'username', passwordVariable: 'password')]) {
					sh 'docker login -u $username -p $password'
					sh 'docker push akhilzemoso/be_transaction_jenkins:latest'
				}         
			}
		}
		stage ('Invoking helm build') {
		    build job: 'fgt'
	    	}
	}
}

// pipeline{
// 	agent{
// 		kubernetes {		    
// yaml '''
// apiVersion: v1
// kind: Pod
// spec:
//   containers:
//   - name: fgt-java
//     image: maven:3.8.1-adoptopenjdk-11
//     command:
//     - cat
//     tty: true
//   - name: fgt-docker
//     image: docker:19.03
//     command:
//     - cat
//     tty: true
//     volumeMounts:
//     - name: dockersock
//       mountPath: /var/run/docker.sock
//   volumes:
//     - name: dockersock
//       hostPath:
//         path: /var/run/docker.sock
// '''    
// 		}
// 	}
			
// 	environment {
// 		VERSION = "${env.GIT_COMMIT}"
// 		DOCKERHUB_CREDENTIALS= credentials('akhil-dockerhub')
// 		MY_KUBECONFIG = credentials('master2-rishmita')
// 	}
// 	stages{
// 		stage('Checkout Source') {
// 			steps {
// 				git 'https://github.com/akhilbommu-zemoso/CI-CD-FGT-transaction-be'
// 			}
//     		}
// 		stage('Build Jar'){
// 			steps{
// 				container('fgt-java'){
// 					sh 'mvn -B -DskipTests clean package'
// 				}    
// 			}
// 		}
// 		stage('Build Docker'){
// 			steps{
// 				container('fgt-docker'){
// 					sh 'docker build -t akhilzemoso/be_transaction_jenkins:${VERSION} .'
// 				}
// 			}  
// 	    	}
	    
// 		stage('Push Docker'){
// 			steps{
// 				container('fgt-docker'){
// 					sh 'ls'
// 				    	withCredentials([usernamePassword(credentialsId: 'akhil-dockerhub', usernameVariable: 'username', passwordVariable: 'password')]) {
// 				       		sh 'docker login -u $username -p $password'
// 						sh 'docker push akhilzemoso/be_transaction_jenkins:${VERSION}'
// 					}	           
// 				}
// 			}
// 		}
// 	}    
// 	post{
// 		always{
// 			container('fgt-docker'){
// 				sh 'docker logout'
// 			}
// 		}
// 	}
// }
