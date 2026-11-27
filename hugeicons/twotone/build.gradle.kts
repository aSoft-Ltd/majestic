plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    kotlin("plugin.compose")
    id("tz.co.asoft.library")
}

description = "Hugeicons twotone icons"

android {
    namespace = "tz.co.asoft.majestic.hugeicons.twotone"
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
    packageOfResClass = "majestic.hugeicons.twotone"
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