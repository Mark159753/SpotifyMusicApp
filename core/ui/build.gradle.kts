plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id(Libraries.hiltPlugin)
}

android {
    namespace = "com.example.ui"
    compileSdk = SdkVersions.COMPILE_SDK

    defaultConfig {
        minSdk = SdkVersions.MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = VersionJava.COMPILE
        targetCompatibility = VersionJava.COMPILE
    }
    kotlinOptions {
        jvmTarget = VersionJava.TARGET
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompilerExtension
    }
}

dependencies {

    coreUi()
    testImplementation(Libraries.junit)
    androidTests()
    debugImplementations()

    //Hilt
    hilt()

    spotifyAuth()

    //Project modules
    implementation(project(":core:common"))
}