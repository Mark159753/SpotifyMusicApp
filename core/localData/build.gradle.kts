plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id(Libraries.hiltPlugin)
    id(Libraries.protobufPlugin) version Versions.protobufPlugin
}

android {
    namespace = "com.example.localdata"
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

    // Add generated code folder to app module source set
    sourceSets.getByName("main")
        .java.srcDirs("${protobuf.generatedFilesBaseDir}/main/javalite")

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

    room()

    dataStore()

    protobuf()

    gson()

    //Project modules
    implementation(project(":core:common"))
}

protobuf {
    protoc {
        artifact = Libraries.protoBuf
    }

    generateProtoTasks {
        all().forEach { task ->

            task.builtins {
                create("java") {
                    option("lite")
                }
            }
//            task.builtins {
//                java {
//
//                }
//            }
        }
    }
}