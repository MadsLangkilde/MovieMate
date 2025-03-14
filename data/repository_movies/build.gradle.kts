import com.madslangkilde.convention.implementation

plugins {
    alias(libs.plugins.moviemate.data.server.repository)
}

android {
    namespace = "com.madslangkilde.data.repository_movies"
}

dependencies {
    implementation(libs.realm.base)
    implementation(project(":data:repository_base_server"))
    implementation(project(":data:database_provider"))
    implementation(project(":domain:repository_movies"))
}