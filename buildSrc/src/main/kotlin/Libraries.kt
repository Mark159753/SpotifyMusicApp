object Versions{
    const val composeCompilerExtension = "1.4.3"

    const val coreKtx = "1.12.0"
    const val lifecycleRuntimeKtx = "2.6.2"
    const val activityCompose = "1.7.2"
    const val composeBom = "2023.03.00"

    const val junit = "4.13.2"
    const val extJunit = "1.1.5"
    const val espressoCore = "3.5.1"

    //Navigation
    const val navigation = "2.7.2"

    //Hilt
    const val hilt = "2.48"
    const val hiltWork = "1.0.0"
    const val hiltNavigation = "1.0.0"

    // Retrofit
    const val retrofit = "2.9.0"
    const val okhttp = "4.10.0"

    //DataStore
    const val dataStorePreferences = "1.0.0"
    const val dataStore = "1.0.0"

    //Protobuf Plugin
    const val protobufPlugin = "0.9.3"

    //SplashScreen
    const val splashScreen = "1.0.1"

    //Paging
    const val paging = "3.1.1"
    const val pagingCompose = "3.2.0"

    //WorkManager
    const val work = "2.8.1"

    //Coil
    const val coil = "2.4.0"

    //Room
    const val room = "2.5.2"

    //Spotify Auth
    const val spotifyAuth = "1.2.5"

    //Kotlin immutable collections
    const val immutableCollections = "0.3.5"

    //Gson
    const val gson = "2.10.1"

    //Protobuf Lib
    const val protobufLib = "3.23.1"

    //PlaceHolder
    const val placeHolder = "0.33.1-alpha"
}

object Libraries{
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val ui = "androidx.compose.ui:ui"
    const val uiGraphics = "androidx.compose.ui:ui-graphics"
    const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
    const val material3 = "androidx.compose.material3:material3"
    const val material2 = "androidx.compose.material:material"
    const val junit = "junit:junit:${Versions.junit}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
    const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4"
    const val uiTooling = "androidx.compose.ui:ui-tooling"
    const val uiTestManifest = "androidx.compose.ui:ui-test-manifest"

    const val lifecycleRuntimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:${Versions.lifecycleRuntimeKtx}"

    // Navigation
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navigation}"

    //Hilt
    const val hiltPlugin = "com.google.dagger.hilt.android"
    const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigation}"
    const val hiltWork = "androidx.hilt:hilt-work:${Versions.hiltWork}"
    const val hiltWorkKapt = "androidx.hilt:hilt-compiler:${Versions.hiltWork}"
    const val hiltKapt = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    //Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLogger= "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    //DataStore Preferences
    const val dataStorePreferences = "androidx.datastore:datastore-preferences:${Versions.dataStorePreferences}"
    const val dataStore = "androidx.datastore:datastore:${Versions.dataStorePreferences}"

    //SplashScreen
    const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"

    //Paging3
    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
    const val pagingCompose = "androidx.paging:paging-compose:${Versions.pagingCompose}"

    //Work Manager
    const val workManager = "androidx.work:work-runtime-ktx:${Versions.work}"

    //Coil
    const val coil = "io.coil-kt:coil-compose:${Versions.coil}"

    //Room
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomAnnotationProcessor = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomPaging = "androidx.room:room-paging:${Versions.room}"

    //Spotify Auth
    const val spotifyAuth = "com.spotify.android:auth:${Versions.spotifyAuth}"

    //Kotlin immutable collections
    const val immutableCollections = "org.jetbrains.kotlinx:kotlinx-collections-immutable:${Versions.immutableCollections}"

    //Gson
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    //Protobuf Plugin
    const val protobufPlugin = "com.google.protobuf"

    //Protobuf Lib
    const val protobufLib = "com.google.protobuf:protobuf-javalite:${Versions.protobufLib}"
    const val protoBuf = "com.google.protobuf:protoc:${Versions.protobufLib}"

    //PlaceHolder
    const val placeHolder = "com.google.accompanist:accompanist-placeholder-material:${Versions.placeHolder}"
}