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

description = "The majestic design system implementation from asoft for time, dates and calendars"

android {
    namespace = "tz.co.asoft.academia.majestic.input.chrono"
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
        library()
//        compilations.all {
//            compileTaskProvider {
//                compilerOptions.jvmTarget = JvmTarget.JVM_17
//            }
//        }
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

    wasmJs { browser() }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
//    macosX64()
//    macosArm64()

    sourceSets {
        commonMain.dependencies {
            api(projects.majesticTheme)
            api(projects.majesticScreen)
            api(projects.majesticInputChoice)
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)
            api(compose.materialIconsExtended)
            api(libs.cinematic.live.compose)
            api(kotlinx.coroutines.core)
            api(libs.symphony.input.core)?.because("We need fields and forms")
            api(kotlinx.datetime)?.because("We need access to date time")
            api(libs.kiota.file.manager.core)?.because("Blog Editor needs to be able to pick images")
//            api(libs.kiota.file.compose)?.because("Blog Editor needs to be able to render pickerd images")
            // Remove this dependency when asoft libraries have been published afresh
            api("tz.co.asoft:kiota-file-compose:3.0.14-RC")?.because("Blog Editor needs to be able to render pickerd images")
//            implementation(libs.krono.kotlinx)
//            implementation(coil.compose)
//            implementation(coil.network.ktor)
            api(compose.components.resources)
        }

        commonTest.dependencies {
            implementation(libs.kommander.coroutines)
            implementation(compose.uiTest)
        }

        androidMain.dependencies {
//            implementation("tz.co.asoft:kiota-file-compose-android:${libs.versions.asoft.get()}")?.because("We need ComponentActivity for android file picker")
//            implementation(androidx.activity.ktx)?.because("We need ComponentActivity for android file picker")
        }
    }
}
