plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.ksp) apply false // ✅ KSP 1.9.22-1.0.20 for Kotlin 1.9.22 compatibility

    id("com.google.gms.google-services") version libs.versions.googleServices.get() apply false
}
