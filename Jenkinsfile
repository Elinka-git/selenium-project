pipeline {
	agent any
	tools {
		maven 'maven'
		jdk 'jdk1.8.0_251'
	}
	
	parameters {
		string(name: 'APP_VERSION', defaultValue: '1.0.0', description: 'select application version for deployment')
		choice(name: 'APP_VERSION', choices: ['1.0.0', '1.1.0', '2.0.1'], description: 'select application version for deployment')
	}
	
	stages {
		stage('Deploy Application') {
			steps {
				echo 'Deploying....'
		}
	}
	
		stage('Execute Automated Test') {
			steps {
				echo 'Testing..'
				bat 'mvn -Dtestng.dtd.http=true clean test'
			}
		}
	}
	
	post {
		always {
			step([$class: 'Publisher', reportFilenamePattern: 'target/surefire-reports/testng-results.xml'])
		}
	}