@file:OptIn(ExperimentalComposeLibrary::class)

import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree


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
    namespace = "tz.co.asoft.academia.majestic.screen"
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
    applyHierarchyTemplate {
        sourceSetTrees(KotlinSourceSetTree.main, KotlinSourceSetTree.test)
        common {
            withAndroidTarget()
            group("skiko") {
                withJvm()
                withJs()
                withWasmJs()
                withIosX64()
                withIosArm64()
                withIosSimulatorArm64()
                withMacosX64()
                withMacosArm64()
            }
        }
    }

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
    js(IR) {
        browser()
        nodejs()
    }

    wasmJs { browser() } // until coil and kotlinx-datetime supports this, we ain't gonna
    iosX64()
    iosArm64()
    iosSimulatorArm64()
    macosX64()
    macosArm64()

    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(kotlinx.serialization.core)
            api(ktor.client.core)?.because("We need the HttpConnectionVerifier to make http calls to verify the connection")
            api(libs.cinematic.live.core)?.because("Connection observer requires to communicate its state")
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
            implementation(kotlin("test-junit5"))
            implementation(libs.kommander.core)
            implementation(libs.kommander.coroutines)
        }

        jvmTest.dependencies {
            api(ktor.client.cio)?.because("We need to test if we can verify an active connection")
        }
    }
}
