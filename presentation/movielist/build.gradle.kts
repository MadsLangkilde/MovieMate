import com.madslangkilde.convention.implementation

plugins {
    alias(libs.plugins.moviemate.presentation)
}

android {
    namespace = "com.madslangkilde.movielist"
}

dependencies {
    implementation(project(":domain:repository_movielist"))
}