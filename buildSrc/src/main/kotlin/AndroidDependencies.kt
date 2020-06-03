object AndroidDependencies {
    const val APPCOMPAT =
        "androidx.appcompat:appcompat:${AndroidDependenciesVersions.APP_COMPAT_VERSION}"
    const val DESIGN =
        "com.google.android.material:material:${AndroidDependenciesVersions.MATERIAL_DESIGN_VERSION}"
    const val SUPPORT_CONSTRAINT_LAYOUT =
        "androidx.constraintlayout:constraintlayout:${AndroidDependenciesVersions.CONSTRAINT_LAYOUT_VERSION}"
    const val COORDINATOR_LAYOUT =
        "androidx.coordinatorlayout:coordinatorlayout:${AndroidDependenciesVersions.COORDINATOR_LAYOUT}"
    const val CARDVIEW =
        "androidx.cardview:cardview:${AndroidDependenciesVersions.CARD_VIEW_VERSION}"
    const val RECYCLERVIEW =
        "androidx.recyclerview:recyclerview:${AndroidDependenciesVersions.RECYCLER_VIEW_VERSION}"
    const val MATERIAL =
        "com.google.android.material:material:${AndroidDependenciesVersions.MATERIAL}"
    const val CONSTRAINTLAYOUT =
        "androidx.constraintlayout:constraintlayout:${AndroidDependenciesVersions.CONSTRAINT_LAYOUT_VERSION}"
}

private object AndroidDependenciesVersions {
    const val CONSTRAINT_LAYOUT_VERSION = "1.1.3"
    const val COORDINATOR_LAYOUT = "1.0.0"
    const val APP_COMPAT_VERSION = "1.1.0"
    const val MATERIAL_DESIGN_VERSION = "1.0.0"
    const val MATERIAL = "1.1.0-alpha09"
    const val CARD_VIEW_VERSION = "1.0.0"
    const val RECYCLER_VIEW_VERSION = "1.0.0"
}