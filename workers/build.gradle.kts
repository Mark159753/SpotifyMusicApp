plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id(Libraries.hiltPlugin)
}

android {
    namespace = "com.example.workers"
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
}

dependencies {

    implementation(Libraries.coreKtx)
    testImplementation(Libraries.junit)
    androidTestImplementation(Libraries.extJunit)
    androidTestImplementation(Libraries.espressoCore)

    //Hilt
    hilt()

    workManager()

    //Project modules
    implementation(project(":core:common"))
    implementation(project(":core:data"))
}