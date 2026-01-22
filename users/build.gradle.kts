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

description = "The users module"

android {
    namespace = "tz.co.asoft.academia.majestic.users"
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
    iosTargets()

    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.foundation)
            api(compose.material3)?.because("We need to access LocalContentColor")
            api(compose.materialIconsExtended)?.because("We need default icons for table")
            api(libs.symphony.table)?.because("We need a table manager of some sorts")
            api(libs.cinematic.live.compose)?.because("We need to watchAsState")
            api(projects.majesticTheme)?.because("We need an instance of ColorPair")
            api(projects.majesticScreen)?.because("We need access to NoRippleInteractionSource")
            api(compose.materialIconsExtended)
            api(libs.kiota.file.manager.core)
            api(projects.majesticInputCore)
            api(projects.majesticInputText)
            api(projects.majesticInputChoice)
            api(projects.majesticInputColor)
            api(projects.majesticInputPhone)
            api(projects.majesticInputChrono)?.because("We need good ui for date, time and calendar pickers")
            api(projects.majesticTable)
            api(projects.majesticGraphs)
            api(projects.majesticScreen)
            api(projects.majesticDrawers)
            api(projects.majesticLoaders)
            api(projects.majesticOverlays)
            api(libs.nation.countries)
            api(libs.nation.flags.compose)
            api(projects.majesticShared)?.because("We need to for shared dashboard components, reducing duplications")
            api(projects.majesticIcons)
            api(libs.nation.currencies)
            api(libs.captain.router.compose.core)
            api(github.compottie)
            api(github.compottie.dot)
            api(github.compottie.network)
            api(github.compottie.resources)
            api(kotlinx.datetime)?.because("Required for date time picker")
            implementation(libs.cinematic.live.compose)
            implementation(kotlinx.coroutines.core)
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
