object TestLibraryDependencies {
    const val jUnit = "junit:junit:${TestLibraryVersion.jUnitVersion}"
    const val jUnitExt = "androidx.test.ext:junit:${TestLibraryVersion.jUnitExtVersion}"
    const val kotlinTest =
        "org.jetbrains.kotlin:kotlin-test-junit:${TestLibraryVersion.kotlinVersion}"
    const val mockK = "io.mockk:mockk:${TestLibraryVersion.mockKVersion}"
    const val testRunner = "androidx.test:runner:${TestLibraryVersion.testRunnerVersion}"
    const val okhttp3IdlingResources =
        "com.jakewharton.espresso:okhttp3-idling-resource:${TestLibraryVersion.okhttp3IdlingResourcesVersion}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${TestLibraryVersion.coroutinesCoreVersion}"
    const val mockWebServer =
        "com.squareup.okhttp3:mockwebserver:${TestLibraryVersion.okHttpVersion}"
    const val kotlinxCoroutinestest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${TestLibraryVersion.kotlinXCoroutinesTestVersion}"
    const val espressoCore =
        "androidx.test.espresso:espresso-core:${TestLibraryVersion.espressoCoreVersion}"
    const val espressoContrib =
        "androidx.test.espresso:espresso-contrib:${TestLibraryVersion.espressoCoreVersion}"
    const val espressoIntents =
        "androidx.test.espresso:espresso-intents:${TestLibraryVersion.espressoCoreVersion}"
    const val roomTesting = "androidx.room:room-testing:${TestLibraryVersion.roomVersion}"
}

private object TestLibraryVersion {
    const val jUnitVersion = "4.12"
    const val jUnitExtVersion = "1.1.1"
    const val okHttpVersion = "4.2.1"
    const val coroutinesCoreVersion = "1.3.2"
    const val testRunnerVersion = "1.2.0"
    const val okhttp3IdlingResourcesVersion = "1.0.0"
    const val mockKVersion = "1.9"
    const val kotlinXCoroutinesTestVersion = "1.3.5"
    const val espressoCoreVersion = "3.2.0"
    const val roomVersion = "2.2.5"
    const val kotlinVersion = "1.3.72"
}