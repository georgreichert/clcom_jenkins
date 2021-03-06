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
                script {
                    try {
                        sh 'javac Tests.java'
                        sh 'java -ea Tests'
                    }
                    catch (exc) {
                        echo 'Testing failed!'
                        echo exc.toString()
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }
        stage('confirmation') {
            steps {
                script {
                    if (currentBuild.result == 'UNSTABLE') {
                        mail to: 'if20b260@technikum-wien.at',
                             subject: "Unstable pipeline - Confirmation needed: ${currentBuild.fullDisplayName}",
                             body: "UNSTABLE - One or more tests failed trying to build, manual confirmation needed for deploy stage ${env.JENKINS_URL}job/CLCOM-pipeline/job/main/"
                        input 'One ore more tests failed, do you want to continue to deploy stage?'
                    }
                }
            }
        }
        stage('deploy') {
            steps {
                echo 'Deploying to external test server...'
            }
        }
    }
    post {
        always {
            echo 'Cleaning up'
            deleteDir()
        }
        success {
            mail to: 'if20b260@technikum-wien.at',
                 subject: "Successful Pipeline: ${currentBuild.fullDisplayName}",
                 body: "SUCCESS - Everything went well with ${env.BUILD_URL}"
        }
        unstable {
            mail to: 'if20b260@technikum-wien.at',
                 subject: "Unstable Pipeline: ${currentBuild.fullDisplayName}",
                 body: "UNSTABLE - build was deployed by user input ${env.BUILD_URL}"
        }
        failure {
            mail to: 'if20b260@technikum-wien.at',
                 subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
                 body: "FAILURE - Something is wrong with ${env.BUILD_URL}"
        }
        changed {
            echo 'There have been changes since the last build.'
        }
    }
}