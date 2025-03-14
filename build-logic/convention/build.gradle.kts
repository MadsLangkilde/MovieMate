import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.madslangkilde.build_logic.convention"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "moviemate.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryUiBase") {
            id = "moviemate.android.library.uibase"
            implementationClass = "AndroidLibraryUiBaseConventionPlugin"
        }
        register("androidApplication") {
            id = "moviemate.android.app"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidPresentation") {
            id = "moviemate.android.presentation"
            implementationClass = "AndroidPresentationConventionPlugin"
        }
        register("androidDataServerRepository") {
            id = "moviemate.android.data.server.repository"
            implementationClass = "AndroidDataServerRepositoryConventionPlugin"
        }
        register("androidDomain") {
            id = "moviemate.android.domain"
            implementationClass = "AndroidDomainConventionPlugin"
        }
    }
}