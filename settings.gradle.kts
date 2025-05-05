pluginManagement {
    repositories {
        gradlePluginPortal() // ✅ Must be first to resolve KSP plugin correctly
        google()
        mavenCentral()
        maven("https://plugins.gradle.org/m2/") // ✅ Optional backup
    }

    plugins {
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
