pipeline {
    agent any

    stages {
            stage ('Compile Stage') {
                steps {
                    bat 'mvn clean install'
                }
            }
        stage('Test') {
            steps {
                bat "mvn -D clean test -Dcucumber.tags=${RunByTag}"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    publishHTML([allowMissing: false,
                    alwaysLinkToLastBuild: false,
                    keepAll: false,
                    reportDir: '',
                    reportFiles: 'index.html',
                    reportName: 'HTML Report',
                    reportTitles: '',
                    useWrapperFileDirectly: true])
                }
            }
        }
    }
}