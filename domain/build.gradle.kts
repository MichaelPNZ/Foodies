plugins {
    alias(libs.plugins.jetbrainsKotlinJvm)
}

dependencies {
    implementation(project(":utils"))

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.javax.inject)
}

