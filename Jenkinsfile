pipeline {
    agent any

ienvironment {
    AWS_REGION = 'us-east-1'
    ECR_REGISTRY = '423671573423.dkr.ecr.us-east-1.amazonaws.com'
    JAVA_HOME = '/usr/lib/jvm/java-21-amazon-corretto.x86_64'
    PATH = "/usr/lib/jvm/java-21-amazon-corretto.x86_64/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"
}}

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/muralitanneru/SpringBoot_Microservice_Project.git'
            }
        }

        stage('Login to AWS ECR') {
            steps {
                sh '''
                aws ecr get-login-password --region ${AWS_REGION} | docker login --username AWS --password-stdin ${ECR_REGISTRY}
                '''
            }
        }

        stage('Build Push Deploy Service Registry') {
            steps {
                sh '''
                cd service-registery
                mvn clean package -DskipTests
                docker build -t service-registry -f dockerfile .
                docker tag service-registry:latest ${ECR_REGISTRY}/service-registry:latest
                docker push ${ECR_REGISTRY}/service-registry:latest
                kubectl apply -f k8s/
                '''
            }
        }

        stage('Build Push Deploy Config Server') {
            steps {
                sh '''
                cd cloud-config-server
                mvn clean package -DskipTests
                docker build -t config-server -f dockerfile .
                docker tag config-server:latest ${ECR_REGISTRY}/config-server:latest
                docker push ${ECR_REGISTRY}/config-server:latest
                kubectl apply -f k8s/
                '''
            }
        }

        stage('Deploy Zipkin') {
            steps {
                sh '''
                cd zipkin-server
                kubectl apply -f k8s/
                '''
            }
        }

        stage('Build Push Deploy Security Service') {
            steps {
                sh '''
                cd security-service
                mvn clean package -DskipTests
                docker build -t security-service -f dockerfile .
                docker tag security-service:latest ${ECR_REGISTRY}/security-service:latest
                docker push ${ECR_REGISTRY}/security-service:latest
                kubectl apply -f k8s/
                '''
            }
        }

        stage('Build Push Deploy Department Service') {
            steps {
                sh '''
                cd department-service
                mvn clean package -DskipTests
                docker build -t department-service -f dockerfile .
                docker tag department-service:latest ${ECR_REGISTRY}/department-service:latest
                docker push ${ECR_REGISTRY}/department-service:latest
                kubectl apply -f k8s/
                '''
            }
        }

        stage('Build Push Deploy User Service') {
            steps {
                sh '''
                cd user-service
                mvn clean package -DskipTests
                docker build -t user-service -f dockerfile .
                docker tag user-service:latest ${ECR_REGISTRY}/user-service:latest
                docker push ${ECR_REGISTRY}/user-service:latest
                kubectl apply -f k8s/
                '''
            }
        }

        stage('Build Push Deploy API Gateway') {
            steps {
                sh '''
                cd gateway-service
                mvn clean package -DskipTests
                docker build -t api-gateway -f dockerfile .
                docker tag api-gateway:latest ${ECR_REGISTRY}/api-gateway:latest
                docker push ${ECR_REGISTRY}/api-gateway:latest
                kubectl apply -f k8s/
                '''
            }
        }

        stage('Verify Deployment') {
            steps {
                sh '''
                kubectl get nodes
                kubectl get pods
                kubectl get svc
                '''
            }
        }
    }
}
