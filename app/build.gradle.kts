plugins {
    alias(libs.plugins.moviemate.application)
}

android {
    namespace = "com.madslangkilde.moviemate"
}

dependencies {
    implementation(project(":presentation:start"))
    implementation(project(":presentation:movielist"))
    implementation(project(":presentation:search"))
    implementation(project(":presentation:edit_favorite_movie"))
    implementation(project(":data:database_provider"))
    implementation(project(":data:repository_base_server"))
    implementation(project(":data:repository_movies"))
    implementation(project(":data:repository_movielist"))
    implementation(project(":domain:repository_movies"))
    implementation(project(":domain:repository_movielist"))
}