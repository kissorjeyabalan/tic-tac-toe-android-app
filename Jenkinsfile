pipeline {
  agent {
    node {
      label 'SOME_NODE'
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

  stages {
    stage("Notify Start") {
      steps {
        emailext body: 'Url ${env.BUILD_URL}', recipientProviders: [[$class: 'RequesterRecipientProvider']], subject: 'Started Pipeline: ${currentBuild.fullDisplayName}'
      }
    }

    stage("Build AS") {
      steps {
        script {
          sh "./gradlew assembleRelease --info --stacktrace"
        } 
      }
    }
  }
  
  post {
    failure {
      emailext attachLog: true, body: 'Something is wrong with ${env.BUILD_URL}', recipientProviders: [[$class: 'CulpritsRecipientProvider']], subject: 'Failed Pipeline: ${currentBuild.fullDisplayName}'
    }

    success {
      androidApkUpload apkFilesPattern: '**/*.apk', deobfuscationFilesPattern: '**/mapping.txt', googleCredentialsId: 'api-6886201687893048698-196111', rolloutPercentage: '100%', trackName: 'beta'
      emailext attachmentsPattern: '**/*.apk', body: 'Url ${env.BUILD_URL}', recipientProviders: [[$class: 'CulpritsRecipientProvider']], subject: 'Completed Pipeline: ${currentBuild.fullDisplayName}'
    }
  }
}
