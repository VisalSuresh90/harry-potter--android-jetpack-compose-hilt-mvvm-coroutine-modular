object Dependencies {


    val coreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx }" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat }" }
    val material by lazy { "com.google.android.material:material:${Versions.material }" }
    val lifeCycleRuntimeKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleRuntimeKtx }" }
    val activityCompose by lazy { "androidx.activity:activity-compose:${Versions.activityCompose }" }
    val composeBom by lazy { "androidx.compose:compose-bom:${Versions.compose }" }
    val iconsExtend by lazy { "androidx.compose.material:material-icons-extended:${Versions.iconsExtend }" }
    val composeUi by lazy { "androidx.compose.ui:ui:" }
    val composeUiGraphics by lazy { "androidx.compose.ui:ui-graphics:" }
    val composeUiToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.composeUiToolingPreview }" }
    val composeUiMaterial3 by lazy { "androidx.compose.material3:material3:${Versions.composeUiMaterial3 }" }
    val navigationCompose by lazy { "androidx.navigation:navigation-compose:${Versions.navigationCompose }" }
    val composeTestJUnit4 by lazy { "androidx.compose.ui:ui-test-junit4:${Versions.compose }" }
    val composeUiTooling by lazy { "androidx.compose.ui:ui-tooling:${Versions.compose }" }
    val composeUiTestManifest by lazy { "androidx.compose.ui:ui-test-manifest:${Versions.compose }" }
    val lifecycleViewModelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleViewModelKtx }" }


    //hilt
    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hilt }" }
    val hiltAndroidCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt }" }
    val hiltCompiler by lazy { "androidx.hilt:hilt-compiler:${Versions.hiltCompiler }" }
    val hiltNavigationCompose by lazy { "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose }" }

    //network
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit }" }
    val okhttp by lazy { "com.squareup.okhttp3:okhttp:${Versions.okhttp }" }
    val gsonConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.gsonConverter }" }
    val moshi by lazy { "com.squareup.moshi:moshi-kotlin:${Versions.moshi }" }
    val moshiConverter by lazy { "com.squareup.retrofit2:converter-moshi:${Versions.moshiConverter }" }
    val loggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor }" }

    //coroutines
    val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines }" }
    val coroutinesAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines }" }

    //splashscreen
    val splashScreen by lazy { "androidx.core:core-splashscreen:${Versions.splashScreen }" }

    // image loader
    val coil by lazy { "io.coil-kt:coil-compose:${Versions.coil }" }


}

object  Modules {
    const val utilities = ":utilities"

}