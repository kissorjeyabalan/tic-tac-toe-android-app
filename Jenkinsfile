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
        emailext body: '$DEFAULT_BODY', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider'], [$class: 'CulpritsRecipientProvider']], subject: '$DEFAULT_SUBJECT'
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
      emailext attachmentsPattern: '**/*.apk', attachLog: true, body: '$DEFAULT_BODY', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider'], [$class: 'CulpritsRecipientProvider']], subject: '$DEFAULT_SUBJECT'
    }

    success {
      emailext attachmentsPattern: '**/*.apk', body: '$DEFAULT_BODY', recipientProviders: [[$class: 'DevelopersRecipientProvider'], [$class: 'RequesterRecipientProvider'], [$class: 'CulpritsRecipientProvider']], subject: '$DEFAULT_SUBJECT'
    }
  }
}
