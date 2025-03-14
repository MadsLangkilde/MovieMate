plugins {
    alias(libs.plugins.moviemate.data.server.repository)
}

android {
    namespace = "com.madslangkilde.data.repository_movies"
}

dependencies {
    implementation(libs.realm.base)
    implementation(project(":data:database_provider"))
    implementation(project(":domain:repository_movielist"))
}