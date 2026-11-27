plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    kotlin("plugin.compose")
    id("tz.co.asoft.library")
}

description = "Hugeicons solid-sharp icons"

android {
    namespace = "tz.co.asoft.majestic.hugeicons.solid.sharp"
    compileSdk = androidx.versions.compile.sdk.get().toInt()
    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "majestic.hugeicons.solid.sharp"
}

kotlin {
    androidTarget()
    jvm()
    wasmJs { browser() }
    iosTargets()
    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.components.resources)
        }
    }
}