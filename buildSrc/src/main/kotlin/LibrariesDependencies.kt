object LibrariesDependencies {

    const val DAGGER = "com.google.dagger:dagger:${LibraryVersion.DAGGER_VERSION}"
    const val DAGGER_ANDROID = "com.google.dagger:dagger-android:${LibraryVersion.DAGGER_VERSION}"
    const val DAGGER_ANDROID_SUPPORT =
        "com.google.dagger:dagger-android-support:${LibraryVersion.DAGGER_ANDROID_VERSION}"

    const val DAGGER_KAPT = "com.google.dagger:dagger-compiler:${LibraryVersion.DAGGER_VERSION}"
    const val DAGGER_ANDROID_KAPT =
        "com.google.dagger:dagger-android-processor:${LibraryVersion.DAGGER_ANDROID_VERSION}"
    const val DAGGER_ANNOTATION_PROCESSOR =
        "com.google.dagger:dagger-android-processor:${LibraryVersion.DAGGER_ANDROID_VERSION}"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${LibraryVersion.RETROFIT_VERSION}"
    const val RETROFIT_GSON_CONVERTER =
        "com.squareup.retrofit2:converter-gson:${LibraryVersion.RETROFIT_VERSION}"
    const val RETROFIT_LOGGING_INTERCEPTOR =
        "com.squareup.okhttp3:logging-interceptor:${LibraryVersion.OK_HTTP_VERSION}"

    const val GSON = "com.google.code.gson:gson:${LibraryVersion.GSON_VERSION}"

    const val COROUTINES =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${LibraryVersion.COROUTINES_CORE_VERSION}"

    const val GLIDE = "com.github.bumptech.glide:glide:${LibraryVersion.GLIDE_VERSION}"
    const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${LibraryVersion.GLIDE_VERSION}"

    const val COUNTRY_FLAG =
        "com.github.yesterselga:country-picker-android:${LibraryVersion.COUNTRY_PICKER_VERSION}"


}

private object LibraryVersion {
    const val OK_HTTP_VERSION = "4.2.1"
    const val COROUTINES_CORE_VERSION = "1.3.2"
    const val RETROFIT_VERSION = "2.6.0"
    const val DAGGER_VERSION = "2.25.2"
    const val DAGGER_ANDROID_VERSION = "2.25.2"
    const val GSON_VERSION = "2.8.5"
    const val GLIDE_VERSION = "4.9.0"
    const val COUNTRY_PICKER_VERSION = "1.0"
}