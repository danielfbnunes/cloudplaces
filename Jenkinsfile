pipeline {
  agent any
  tools {
      jdk 'openjdk-8'
      maven 'mvn3'
  }
  stages {
      stages {
        stage('SonarQube analysis') {
             steps {
                withSonarQubeEnv('tqs19-103-sonarQube') {
                    // Optionally use a Maven environment you've configured already
                    withMaven(maven:'mvn3') {
                        sh 'mvn clean package sonar:sonar'
                    }
                }
            }
        }
        stage('Install') {
            steps {
                sh "mvn -U clean test cobertura:cobertura -Dcobertura.report.format=xml"
            }
            post {
                always {
                    junit '**/target/*-reports/TEST-*.xml'
                    step([$class: 'CoberturaPublisher', coberturaReportFile: 'target/site/cobertura/coverage.xml'])
                }
            }
        }
    }
  }
}
