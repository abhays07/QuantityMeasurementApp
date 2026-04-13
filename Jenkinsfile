pipeline {
    agent any

    stages {
        stage('Checkout Repositories') {
            steps {
                script {
                    // 1. Pull Backend code into a folder named 'backend'
                    dir('backend') {
                        git branch: 'feature/UC21-MicroservicesArchitecture', 
                            url: 'https://github.com/abhays07/QuantityMeasurementApp.git'
                    }
                    // 2. Pull Frontend code into a folder named 'frontend'
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
                    // Navigate to the backend folder where docker-compose.yml lives
                    dir('backend') {
                        sh 'docker-compose -p qma down || true'
                        sh 'docker-compose -p qma up -d'
                    }
                }
            }
        }

        stage('Verify') {
            steps {
                sh 'docker ps'
            }
        }
    }
}