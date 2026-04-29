pipeline {
    agent any

    parameters {
        choice(name: 'BROWSER', choices: ['chrome', 'firefox', 'edge'], description: 'Select the browser for test execution')
        choice(name: 'testng', choices: ['testng.xml'], description: 'Select the testng xml file to execute test cases')
    }
    
    environment {
        // The URL established using the Docker Compose network
        SELENIUM_HUB_URL = 'http://selenium-hub:4444'
    }
    
    tools {
        maven 'Maven_3' 
        jdk 'Java_17'
    }

    stages {
        stage('Clean Workspace') {
            steps {
                echo 'Cleaning workspace to ensure a fresh environment...'
                cleanWs() 
            }
        }

        stage('Checkout Code') {
            steps {
                echo 'Checking out source code from develop branch...'
                git branch: 'develop', url: 'https://github.com/virenderkamboj7/Selenium_Webdriver_Framework.git'
            }
        }

        stage('Build Project') {
            steps {
                echo 'Downloading dependencies and compiling the code...'
                // Running compile separately ensures code syntax and dependencies 
                // are valid before spinning up browser instances.
                sh "mvn clean compile"
            }
        }

        stage('Execute Tests') {
            steps {
                echo 'Running TestNG Suite on Selenium Grid...'
                // Running the tests. The -DgridUrl dynamically passes the environment variable to your Java code.
                sh "mvn test -DtestngFile=${testng} -DgridUrl=${SELENIUM_HUB_URL} -Dbrr=${BROWSER}"
            }
        }
    }

    // The post block at the pipeline level evaluates the overall result
    post {
        always {
            echo "Pipeline execution finished. Processing artifacts..."
            // Uncomment these when you want to process reports regardless of pass/fail
            // junit '**/target/surefire-reports/TEST-*.xml'
            // archiveArtifacts 'target/*.jar'
        }
        success {
            echo "✅ Build Success! All stages passed."
        }
        failure {
            echo "❌ Build Failed! Check the console output to see which stage failed."
        }
    }
}