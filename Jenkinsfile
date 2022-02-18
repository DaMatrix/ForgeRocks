String getDiscordMessage() {
    def msg = "**Status:** " + currentBuild.currentResult.toLowerCase() + "\n**Branch:** ${BRANCH_NAME}\n**Changes:**"
    if (!currentBuild.changeSets.isEmpty()) {
        currentBuild.changeSets.first().getLogs().any {
            def line = "\n- `" + it.getCommitId().substring(0, 8) + "` *" + it.getComment().split("\n")[0].replaceAll('(?<!\\\\)([_*~`])', '\\\\$1') + "*"
            if (msg.length() + line.length() <= 1500)   {
                msg += line
                return
            } else {
                return true
            }
        }
    } else {
        msg += "\n- no changes"
    }

    msg += "\n**Artifacts:**"
    currentBuild.rawBuild.getArtifacts().any {
        def line = "\n- [" + it.getDisplayPath() + "](" + env.BUILD_URL + "artifact/" + it.getHref() + ")"
        if (msg.length() + line.length() <= 2000)   {
            msg += line
            return
        } else {
            return true
        }
    }
    return msg
}

pipeline {
    agent {
        label "daporkchop_maven_password"
    }
    tools {
        git "Default"
        jdk "jdk8"
    }
    options {
        buildDiscarder(logRotator(artifactNumToKeepStr: '5'))
    }
    stages {
        stage("Setup") {
            steps {
                //this will cause all projects to be configured, thereby forcing ForgeGradle to do all its expensive workspace setup
                sh "./gradlew projects"
            }
        }
        stage("Build") {
            steps {
                sh "./gradlew build -x test"
            }
            post {
                success {
                    archiveArtifacts artifacts: "build/libs/*-full.jar", fingerprint: true
                }
            }
        }
        stage("Publish") {
            when {
                branch "master"
            }
            steps {
                sh "./gradlew publish -x test -x publishToMavenLocal"
            }
        }
    }

    post {
        always {
            sh "./gradlew --stop"
            deleteDir()

            withCredentials([string(credentialsId: "daporkchop_discord_webhook", variable: "discordWebhook")]) {
                discordSend result: currentBuild.currentResult,
                        description: getDiscordMessage(),
                        link: env.BUILD_URL,
                        title: "ForgeRocks/${BRANCH_NAME} #${BUILD_NUMBER}",
                        webhookURL: "${discordWebhook}"
            }
        }
    }
}
