pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "zomato-sample"

include(":app:customer")
include(":app:restaurant")
include(":app:delivery")

include(":core")
include(":data")
include(":domain")

// Configure modules
project(":app:customer").projectDir = file("app/customer")
project(":app:restaurant").projectDir = file("app/restaurant")
project(":app:delivery").projectDir = file("app/delivery")

project(":core").projectDir = file("core")
project(":data").projectDir = file("data")
project(":domain").projectDir = file("domain")
