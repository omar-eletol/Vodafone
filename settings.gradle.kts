pluginManagement {
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
        maven {
            url = uri("https://maven.pkg.github.com/omar-eletol/Vodafone")
            credentials {
                username = "omar-eletol"
            }
        }
    }

}

rootProject.name = "Vodafone"
include(":app")
include(":core")
include(":data")
include(":features")
include(":features:cityinput")
include(":features:forecastList")
include(":displayingCustomWeatherIcons")
