plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id(Libraries.hiltPlugin)
}

android {
    namespace = "com.example.spotifymusicapp"
    compileSdk = SdkVersions.COMPILE_SDK

    defaultConfig {
        applicationId = "com.example.spotifymusicapp"
        minSdk = SdkVersions.MIN_SDK
        targetSdk = SdkVersions.TARGET_SDK
        versionCode = CodeVersions.VERSION
        versionName = CodeVersions.NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    coreUi()
    testImplementation(Libraries.junit)
    androidTests()
    debugImplementations()

    //Hilt
    hilt()

    //Navigation
    navigation()

    //WorkManager
    workManager()

    //SplashScreen
    splashScreen()

    //Project modules
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":core:localData"))
    implementation(project(":feature:login"))
    implementation(project(":feature:home"))
    implementation(project(":feature:search"))
    implementation(project(":feature:favorites"))
}