pluginManagement {
    repositories {
        gradlePluginPortal() //  Required for most Gradle plugins
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/") // ✅ Optional backup for plugin resolution
    }

    plugins {
        //  Explicit declaration of the KSP plugin
        id("com.google.devtools.ksp") version "1.9.22-1.0.20"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "BasketballApp"
include(":app")
