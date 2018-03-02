pipeline {
  agent {
    node {
      label 'AWS_STANDARD_SLAVE'
    }
  }

  triggers {
    pollSCM('')
    githubPush()
  }
  
  options {
    buildDiscarder(logRotator(numToKeepStr: '1'))
    disableConcurrentBuilds()
    timeout(time: 1, unit: 'HOURS')
  }

  tools {
    gradle "gradle"
  }

  stages {
    stage("Notify Start") {
      steps {
        emailext body: 'Your app is being built!', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider'], [$class: 'CulpritsRecipientProvider']], subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - started!'
      }
    }

    stage("Build AS") {
      steps {
        script {
          sh "gradle clean assembleRelease --info --stacktrace"
        } 
      }
    }

    stage('Deploy') {
      steps {
        androidApkUpload apkFilesPattern: '**/*-release.apk', deobfuscationFilesPattern: '**/mapping.txt', googleCredentialsId: 'api-6886201687893048698-196111', rolloutPercentage: '100%', trackName: 'beta'
      }
    }
  }
  
  post {
    failure {
      emailext attachmentsPattern: '**/*.apk', attachLog: true, body: '$DEFAULT_CONTENT', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider'], [$class: 'CulpritsRecipientProvider']], subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - failure!'
    }

    success {
      emailext attachmentsPattern: '**/*.apk', body: '$DEFAULT_CONTENT', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider'], [$class: 'CulpritsRecipientProvider']], subject: '$PROJECT_NAME - Build # $BUILD_NUMBER - success!'
    }
  }
}