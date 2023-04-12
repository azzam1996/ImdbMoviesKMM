buildscript{
    dependencies {
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.3")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.5")
    }
}
plugins {
    //trick: for the same plugin versions in all sub-modules
    id("com.android.application").version("7.3.1").apply(false)
    id("com.android.library").version("7.3.1").apply(false)
    kotlin("android").version("1.5.31").apply(false)
    kotlin("multiplatform").version("1.5.31").apply(false)
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
