plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
    kotlin("plugin.serialization") version "1.5.31"
}

val coroutinesVersion = "1.5.2"

kotlin {
    android()
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting{
            dependencies{
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion-native-mt")
                implementation("io.ktor:ktor-client-core:1.6.0")
                implementation ("io.ktor:ktor-client-serialization:1.6.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.0.1'")
                implementation("com.squareup.sqldelight:runtime:1.5.3")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting{
            dependencies{
                implementation("io.ktor:ktor-client-android:1.6.7")
                implementation("com.squareup.sqldelight:android-driver:1.5.3")
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependencies{
                implementation("io.ktor:ktor-client-ios:1.6.0")
                implementation("com.squareup.sqldelight:native-driver:1.5.3")
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

sqldelight {
    database("MoviesDatabase") {
        packageName = "com.azzam.imdbmovies.database"
        sourceFolders = listOf("sqldelight")
    }
}

android {
    namespace = "com.azzam.imdbmovies"
    compileSdk = 32
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }
}