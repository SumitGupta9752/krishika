// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        // Add the Google Services classpath
        classpath("com.google.gms:google-services:4.4.2")  // This is necessary for Firebase
    }
}

plugins {
    // These plugins are applied to the app-level build.gradle file.
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.google.gms.google.services) apply false
}
