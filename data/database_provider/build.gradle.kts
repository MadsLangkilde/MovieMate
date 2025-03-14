plugins {
    alias(libs.plugins.moviemate.library)
    id("io.realm.kotlin")
}

android {
    namespace = "com.madslangkilde.database_provider"
}

dependencies {
    implementation(libs.realm.base)
}