// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}

buildscript {
    dependencies {
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.52") // Updated to 2.52
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.25")
    }
}