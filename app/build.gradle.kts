plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "com.infinity.infigen"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.infinity.infigen"
        minSdk = 23
        targetSdk = 35
        versionCode = 2
        versionName = "1.2-cloud"
    }
}
dependencies {
    implementation("androidx.webkit:webkit:1.12.1")
}
