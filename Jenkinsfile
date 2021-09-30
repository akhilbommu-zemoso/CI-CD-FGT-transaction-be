pipeline{
	agent{
		kubernetes {		    
yaml '''
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: fgt-java
    image: maven:3.8.1-adoptopenjdk-11
    command:
    - cat
    tty: true
  - name: fgt-docker
    image: docker:19.03
    command:
    - cat
    tty: true
    volumeMounts:
    - name: dockersock
      mountPath: /var/run/docker.sock
  volumes:
    - name: dockersock
      hostPath:
        path: /var/run/docker.sock
'''    
		}
	}
			
	environment {
		docker_image=""
		DOCKERHUB_CREDENTIALS= credentials('akhil-dockerhub')
		MY_KUBECONFIG = credentials('master2-rishmita')
	}
	stages{
		stage('Checkout Source') {
			steps {
				git 'https://github.com/akhilbommu-zemoso/CI-CD-FGT-budget-be.git'
			}
    		}
		stage('Build Jar'){
			steps{
				container('fgt-java'){
					sh 'mvn -B -DskipTests clean package'
				}    
			}
		}
		stage('Build Docker'){
			steps{
				container('fgt-docker'){
					sh 'docker build -t akhilzemoso/be_budget_jenkins:latest .'
					sh 'docker images'
				}
			}  
	    	}
	    
		stage('Push Docker'){
			steps{
				container('fgt-docker'){
					sh 'ls'
				    	withCredentials([usernamePassword(credentialsId: 'akhil-dockerhub', usernameVariable: 'username', passwordVariable: 'password')]) {
						sh 'echo $PASSWORD'
				       	sh 'docker login -u $username -p $password'
						echo USERNAME
						echo "username is $USERNAME"
						sh 'docker push akhilzemoso/be_budget_jenkins:latest'
					    	}	           
				}
			}
		}
	}    
	post{
		always{
			container('fgt-docker'){
				sh 'docker logout'
			}
		}
	}
}
