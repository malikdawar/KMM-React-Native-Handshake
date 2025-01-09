buildscript {
    extra.apply {
        set("buildToolsVersion", "35.0.0")
        set("minSdkVersion", 24)
        set("compileSdkVersion", 35)
        set("targetSdkVersion", 34)
        set("ndkVersion", "26.1.10909125")
        set("kotlinVersion", "1.9.24")
    }

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle")
        classpath("com.facebook.react:react-native-gradle-plugin")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin")
    }
}

apply(plugin = "com.facebook.react.rootproject")

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    //alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    // alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinCocoapods) apply false
}
