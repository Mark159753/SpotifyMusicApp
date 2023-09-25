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

rootProject.name = "SpotifyMusic"
include(":app")
include(":core:common")
include(":core:data")
include(":core:network")
include(":core:ui")
include(":feature:home")
include(":feature:login")
include(":core:localData")
include(":feature:search")
include(":feature:favorites")
include(":workers")
include(":core:domain")
include(":feature:tracks-list")
