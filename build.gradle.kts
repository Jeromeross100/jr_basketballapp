plugins {
    alias(libs.plugins.android.application) apply false
    id("com.android.library") version "8.2.0" apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.google.services) apply false
    id("com.google.devtools.ksp") version "2.0.21-1.0.27" apply false
}
