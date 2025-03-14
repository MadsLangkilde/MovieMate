pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
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

gradle.startParameter.excludedTaskNames.addAll(listOf(":build-logic:convention:testClasses"))

rootProject.name = "MovieMate"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":presentation:ui_base")
include(":presentation:navigation")
include(":presentation:movielist")
include(":presentation:start")
include(":presentation:search")
include(":domain:repository_movies")
include(":data:repository_movies")
include(":data:repository_base_server")
include(":data:database_provider")
include(":domain:repository_movielist")
include(":data:repository_movielist")
include(":presentation:edit_favorite_movie")
