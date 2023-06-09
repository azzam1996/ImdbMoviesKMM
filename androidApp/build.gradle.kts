plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.azzam.imdbmovies.android"
    compileSdk = 32
    defaultConfig {
        applicationId = "com.azzam.imdbmovies.android"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        viewBinding = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

}

dependencies {
    implementation(project(":shared"))

    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.4.1")
    implementation ("com.google.android.material:material:1.5.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.3")
    testImplementation ("junit:junit:4.13.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")

    //Navigation
    implementation ("androidx.navigation:navigation-dynamic-features-fragment:2.5.1")
    implementation ("androidx.navigation:navigation-ui-ktx:2.5.1")

    // LiveData & ViewModel&lifeCycle
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")
    implementation ("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")

    // Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    // Timber.
    implementation ("com.jakewharton.timber:timber:5.0.1")

    //Koin
    val koin_version= "3.2.1"
    implementation ("io.insert-koin:koin-core:$koin_version")
    testImplementation ("io.insert-koin:koin-test:$koin_version")
    implementation ("io.insert-koin:koin-android:$koin_version")
    implementation ("io.insert-koin:koin-android-compat:$koin_version")

    //Coil
    implementation("io.coil-kt:coil:1.1.0")

    //Ktor
    implementation("io.ktor:ktor-client-android:1.6.7")
}