plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.facebook.react")
}

/**
 * This is the configuration block to customize your React Native Android app.
 * By default you don't need to apply any configuration, just uncomment the lines you need.
 */
react {
    autolinkLibrariesWithApp()
}

/**
 * Set this to true to Run Proguard on Release builds to minify the Java bytecode.
 */
val enableProguardInReleaseBuilds = false

val jscFlavor = "org.webkit:android-jsc:+"

android {
    ndkVersion = rootProject.extra["ndkVersion"] as String
    buildToolsVersion = rootProject.extra["buildToolsVersion"] as String
    compileSdk = rootProject.extra["compileSdkVersion"] as Int

    namespace = "com.awesomeproject"
    defaultConfig {
        applicationId = "com.awesomeproject"
        minSdk = rootProject.extra["minSdkVersion"] as Int
        targetSdk = rootProject.extra["targetSdkVersion"] as Int
        versionCode = 1
        versionName = "1.0"
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
        }
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = enableProguardInReleaseBuilds
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), file("proguard-rules.pro"))
        }
    }
}

dependencies {
    // The version of react-native is set by the React Native Gradle Plugin
    implementation("com.facebook.react:react-android")
    implementation(project(":kmpsharedmodule"))

    if (project.hasProperty("hermesEnabled") && project.property("hermesEnabled") == "true") {
        implementation("com.facebook.react:hermes-android")
    } else {
        implementation(jscFlavor)
    }
}
