@file:OptIn(ExperimentalComposeLibrary::class)

import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
plugins {
    id("com.android.library")
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("org.jetbrains.compose")
    kotlin("plugin.compose")
    id("tz.co.asoft.library")
    alias(kotlinz.plugins.dokka)
}

description = "The majestic design system implementation from asoft"

android {
    namespace = "tz.co.asoft.academia.majestic.input.choice"
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

    wasmJs { browser() }
    iosX64()
    iosArm64()
    iosSimulatorArm64()
//    macosX64()
//    macosArm64()

    sourceSets {
        commonMain.dependencies {
            api(projects.majesticInputCore)
            api(projects.majesticOverlays)?.because("We need popups for select  and dropdowns")
            api(libs.symphony.input.choice)?.because("We need choice fields to choose from")
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
