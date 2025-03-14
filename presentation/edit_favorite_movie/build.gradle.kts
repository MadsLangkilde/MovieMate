import com.madslangkilde.convention.implementation

plugins {
    alias(libs.plugins.moviemate.presentation)
}

android {
    namespace = "com.madslangkilde.edit_favorite_movie"
}

dependencies {
    implementation(project(":domain:repository_movielist"))
}