/**
* JetBrains Space Automation
* This Kotlin-script file lets you automate build activities
* For more info, see https://www.jetbrains.com/help/space/automation.html
*/

job("Build") {
    container(displayName = "Gradle build", image = "openjdk:11") {
        kotlinScript { api ->
            api.gradlew("build")
        }
    }
}