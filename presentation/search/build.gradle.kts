import com.madslangkilde.convention.implementation

plugins {
    alias(libs.plugins.moviemate.presentation)
}

android {
    namespace = "com.madslangkilde.search"
}

dependencies {
    implementation(project(":domain:repository_movies"))
    implementation(project(":domain:repository_movielist"))
}