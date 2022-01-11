pipeline {
    agent { docker { image 'maven:3.8.4-openjdk-11-slim' } }
    stages {
        stage('build') {
            steps {
                sh 'javac Program.java'
                sh 'java Program'
            }
        }
        stage('test') {
            steps {
                sh 'javac Tests.java'
                sh 'java -ea Tests'
            }
        }
    }
}