@file:OptIn(ExperimentalComposeLibrary::class)

import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSetTree
import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension
import org.jetbrains.kotlin.gradle.targets.js.npm.tasks.KotlinNpmInstallTask

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
    namespace = "tz.co.asoft.academia.majestic.input.core"
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
        compilations.all {
            compileTaskProvider {
                compilerOptions.jvmTarget = JvmTarget.JVM_17
            }
        }
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
        }
    }
}

rootProject.the<NodeJsRootExtension>().apply {
    version = npm.versions.node.version.get()
    downloadBaseUrl = npm.versions.node.url.get()
}

rootProject.tasks.withType<KotlinNpmInstallTask>().configureEach {
    args.add("--ignore-engines")
}