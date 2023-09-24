import org.gradle.kotlin.dsl.DependencyHandlerScope

fun DependencyHandlerScope.coreUi(){
    add("implementation", platform(Libraries.composeBom))
    add("implementation", Libraries.coreKtx)
    add("implementation", Libraries.lifecycleRuntimeKtx)
    add("implementation", Libraries.activityCompose)
    add("implementation", Libraries.ui)
    add("implementation", Libraries.uiGraphics)
    add("implementation", Libraries.uiToolingPreview)
    add("implementation", Libraries.material3)
    add("implementation", Libraries.material2)
    add("implementation", Libraries.lifecycleRuntimeCompose)
    add("implementation", Libraries.immutableCollections)
    add("implementation", Libraries.placeHolder)
}

fun DependencyHandlerScope.androidTests(){
    add("androidTestImplementation", Libraries.extJunit)
    add("androidTestImplementation", Libraries.espressoCore)
    add("androidTestImplementation", Libraries.uiTestJunit4)
    add("androidTestImplementation", platform(Libraries.composeBom))
}

fun DependencyHandlerScope.debugImplementations(){
    add("debugImplementation", Libraries.uiTooling)
    add("debugImplementation", Libraries.uiTestManifest)
}

fun DependencyHandlerScope.hilt(){
    add("implementation", Libraries.hilt)
    add("implementation", Libraries.hiltWork)
    add("implementation", Libraries.hiltNavigation)
    add("kapt", Libraries.hiltKapt)
    add("kapt", Libraries.hiltWorkKapt)
}

fun DependencyHandlerScope.retroit(){
    add("implementation", Libraries.retrofit)
    add("implementation", Libraries.converterGson)
    add("implementation", Libraries.okhttp)
    add("implementation", Libraries.okhttpLogger)
}

fun DependencyHandlerScope.room(){
    add("implementation", Libraries.room)
    add("implementation", Libraries.roomKtx)
    add("implementation", Libraries.roomPaging)
    add("annotationProcessor", Libraries.roomAnnotationProcessor)
    add("kapt", Libraries.roomAnnotationProcessor)
}

fun DependencyHandlerScope.dataStore(){
    add("implementation", Libraries.dataStorePreferences)
    add("implementation", Libraries.dataStore)
}

fun DependencyHandlerScope.protobuf(){
    add("implementation", Libraries.protobufLib)
}

fun DependencyHandlerScope.gson(){
    add("implementation", Libraries.gson)
}

fun DependencyHandlerScope.splashScreen(){
    add("implementation", Libraries.splashScreen)
}

fun DependencyHandlerScope.paging(){
    add("implementation", Libraries.paging)
    add("implementation", Libraries.pagingCompose)
}

fun DependencyHandlerScope.workManager(){
    add("implementation", Libraries.workManager)
}

fun DependencyHandlerScope.navigation(){
    add("implementation", Libraries.navigation)
}
fun DependencyHandlerScope.spotifyAuth(){
    add("implementation", Libraries.spotifyAuth)
}