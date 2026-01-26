@file:OptIn(ExperimentalComposeLibrary::class)

import org.jetbrains.compose.ExperimentalComposeLibrary


plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
    kotlin("plugin.compose")
    id("tz.co.asoft.library")
    alias(kotlinz.plugins.dokka)
}

description = "Majestic extention of different kind of overlays like modals, popups and dialogs"

android {
    namespace = "tz.co.asoft.academia.majestic.overlays"
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
    androidTarget {
        // compilations.all {
        //    compileTaskProvider {
        //        compilerOptions.jvmTarget = JvmTarget.JVM_17
        //    }
        // }
    }

    jvm {
        tasks.withType<Test> {
            useJUnitPlatform()
        }
    }
//    js(IR) {
//        browser()
//        nodejs()
//    }

    wasmJs { browser() } // until coil and kotlinx-datetime supports this, we ain't gonna
    iosX64()
    iosArm64()
    iosSimulatorArm64()
//    macosX64()
//    macosArm64()

    sourceSets {
        commonMain.dependencies {
            api(projects.majesticScreen)
            api(compose.runtime)
            api(compose.components.resources)?.because("We need to model menu items with action menus")
            api(compose.foundation)
            api(compose.material3)?.because("We need to provide icons for default Navigation drawer header")
            api(compose.materialIconsExtended)?.because("We need to provide icons for default Navigation drawer header")
        }
    }
}
