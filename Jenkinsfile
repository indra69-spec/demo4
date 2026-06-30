pipeline {
    agent any

    triggers {
        cron('H/10 * * * *')
    }

    tools {
        maven 'Maven'
        jdk 'JDK21'
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/indrajitgupta1620-hub/Demo2.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn compile'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
            post {
                always {
                    junit allowEmptyResults: true, testResults: 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t indrajitgupta/demo2-app:latest .'
            }
        }
        stage('Docker Push') {
            steps {
                sh 'docker push indrajitgupta/demo2-app:latest'
            }
        }
        stage('Deploy to Swarm') {
            steps {
                sh 'docker service update --image indrajitgupta/demo2-app:latest demo2-service'
            }
        }
    }
    post {
        success { echo 'Pipeline succeeded!' }
        failure { echo 'Pipeline failed!' }
    }
}