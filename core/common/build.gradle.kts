import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id(Libraries.hiltPlugin)
}

val apikeyPropertiesFile = rootProject.file("local.properties")
val apikeyProperties = Properties().apply {
    load(apikeyPropertiesFile.inputStream())
}

android {
    namespace = "com.example.common"
    compileSdk = SdkVersions.COMPILE_SDK

    defaultConfig {
        minSdk = SdkVersions.MIN_SDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "ClientId", apikeyProperties["ClientId"] as String)
        buildConfigField("String", "ClientSecret", apikeyProperties["ClientSecret"] as String)
        buildConfigField("String", "RedirectUri", apikeyProperties["RedirectUri"] as String)
        buildConfigField("String", "BASE_URL", apikeyProperties["BASE_URL"] as String)
        buildConfigField("String", "AUTH_URL", apikeyProperties["AUTH_URL"] as String)
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

    buildFeatures{
        buildConfig = true
    }
}

dependencies {

    implementation(Libraries.coreKtx)
//    implementation("androidx.appcompat:appcompat:1.6.1")
//    implementation("com.google.android.material:material:1.9.0")
    testImplementation(Libraries.junit)
    androidTestImplementation(Libraries.extJunit)
    androidTestImplementation(Libraries.espressoCore)

    //Hilt
    hilt()

    //Coil
    api(Libraries.coil)
}