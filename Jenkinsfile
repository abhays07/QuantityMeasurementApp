pipeline {
    agent any

    environment {
        // These variables pull the actual secrets from Jenkins securely
        GOOGLE_CLIENT_ID     = credentials('GOOGLE_CLIENT_ID')
        GOOGLE_CLIENT_SECRET = credentials('GOOGLE_CLIENT_SECRET')
        JWT_SECRETKEY        = credentials('JWT_SECRETKEY')
    }

    stages {
        stage('Checkout Repositories') {
            steps {
                script {
                    dir('backend') {
                        git branch: 'feature/UC21-MicroservicesArchitecture', 
                            url: 'https://github.com/abhays07/QuantityMeasurementApp.git'
                    }
                    dir('frontend') {
                        git branch: 'feature/frontend-microservices', 
                            url: 'https://github.com/abhays07/QuantityMeasurementApp-Frontend.git'
                    }
                }
            }
        }

        stage('Deploy with Docker') {
            steps {
                script {
                    dir('backend') {
                        // This step physically creates the .env file Docker is looking for
                        sh """
                            echo "GOOGLE_CLIENT_ID=${GOOGLE_CLIENT_ID}" > .env
                            echo "GOOGLE_CLIENT_SECRET=${GOOGLE_CLIENT_SECRET}" >> .env
                            echo "JWT_SECRETKEY=${JWT_SECRETKEY}" >> .env
                        """
                        sh 'docker-compose -p qma down || true'
                        sh 'docker-compose -p qma up -d'
                    }
                }
            }
        }
        stage('Verify') {
            steps {
                // Give services 10 seconds to start before verifying
                sleep time: 10, unit: 'SECONDS'
                sh 'docker ps'
            }
        }
    }
}