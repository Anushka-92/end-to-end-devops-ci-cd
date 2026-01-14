pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/your-username/your-repo-name.git'
            }
        }

        stage('Build Application') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t regapp:latest .'
            }
        }

        stage('Run Container') {
            steps {
                sh '''
                docker stop regapp || true
                docker rm regapp || true
                docker run -d --name regapp -p 8080:8080 regapp:latest
                '''
            }
        }
    }
}
