pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building from Jenkinsfile'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing from Jenkinsfile'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying from Jenkinsfile'
            }
        }
    }
}
