pipeline {
    agent any

    tools {
        jdk 'JDK21'
        maven 'Maven3'
    }

    environment {

        IMAGE_NAME = "calculator-app"

        BUILD_TAG = "${env.BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {

                checkout scm
            }
        }

        stage('Build & Compile') {
            steps {
                echo 'Starting Maven Build...'

                sh 'mvn clean compile'
            }
        }

        stage('Unit Tests') {
            steps {
                echo 'Running JUnit Tests...'

                sh 'mvn test'
            }
            post {
                always {

                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Code Coverage (JaCoCo)') {
            steps {
                echo 'Generating Coverage Report...'

                jacoco execPattern: 'target/*.exec'
            }
        }

        stage('Package JAR') {
            steps {
                echo 'Packaging application into JAR...'

                sh 'mvn package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                echo "Building Docker Image: ${IMAGE_NAME}:${BUILD_TAG}"

                sh "docker build -t ${IMAGE_NAME}:${BUILD_TAG} ."
                sh "docker tag ${IMAGE_NAME}:${BUILD_TAG} ${IMAGE_NAME}:latest"
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'

            archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
        }
        failure {
            echo 'Pipeline failed. Please check the logs.'
        }
    }
}