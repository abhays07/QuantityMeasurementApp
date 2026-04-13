pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // This pulls your latest code from GitHub onto the EC2
                git branch: 'feature/UC21-MicroservicesArchitecture', 
                    url: 'https://github.com/abhays07/QuantityMeasurementApp.git'
            }
        }

        stage('Deploy with Docker') {
            steps {
                script {
                    // We use the 'docker-compose' command we just installed manually
                    // 'down' stops old versions, 'up -d' starts new ones in background
                    sh 'docker-compose down || true'
                    sh 'docker-compose up -d'
                }
            }
        }

        stage('Verify') {
            steps {
                // This lets us see the running containers in the Jenkins log
                sh 'docker ps'
            }
        }
    }
}