@file:OptIn(ExperimentalComposeLibrary::class)

import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    kotlin("plugin.compose")
    id("tz.co.asoft.library")
    alias(kotlinz.plugins.dokka)
}

description = "The payments module"

android {
    namespace = "tz.co.asoft.majestic.payments"
    compileSdk = androidx.versions.compile.sdk.get().toInt()
    defaultConfig {
        minSdk = 21 // because of the coil dependency has this as it's min sdk
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    androidTarget { }
    jvm {
        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
//    js(IR) {
//        browser()
//        nodejs()
//    }

    wasmJs { browser() }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
//    macosX64()
//    macosArm64()

    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.components.resources)
            api(compose.material3)?.because("We need to access LocalContentColor")
            api(compose.materialIconsExtended)?.because("We need default icons")
            api(libs.symphony.table)?.because("We need a table manager of some sorts")
            api(libs.cinematic.live.compose)?.because("We need to watchAsState")
            api(projects.majesticTheme)
            api(projects.majesticScreen)
            api(libs.majestic.input.core)
            api(libs.majestic.input.choice)
        }

        commonTest.dependencies {
            implementation(libs.kommander.coroutines)
            implementation(compose.uiTest)
        }

        jvmTest.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}
