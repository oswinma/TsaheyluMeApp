pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        bat './gradlew clean build'
      }
    }

    stage('Deploy') {
      steps {
        sh './gradlew bootRun'
      }
    }

  }
}