plugins {
    id("com.android.library")
    kotlin("multiplatform")
    id("org.jetbrains.compose")
    kotlin("plugin.compose")
    id("tz.co.asoft.library")
}

description = "Hugeicons: the HugeIcon composable and the generated icon names"

android {
    namespace = "tz.co.asoft.majestic.hugeicons"
    compileSdk = androidx.versions.compile.sdk.get().toInt()
    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

kotlin {
    androidTarget()
    jvm()
    wasmJs { browser() }
    iosTargets()
    sourceSets {
        commonMain.dependencies {
            api(compose.runtime)
            api(compose.material3)?.because("HugeIcon draws with Icon and LocalContentColor")
            api(compose.components.resources)
            implementation(projects.majesticHugeiconsStrokeStandard)
            implementation(projects.majesticHugeiconsStrokeRounded)
            implementation(projects.majesticHugeiconsStrokeSharp)
            implementation(projects.majesticHugeiconsSolidStandard)
            implementation(projects.majesticHugeiconsSolidRounded)
            implementation(projects.majesticHugeiconsSolidSharp)
            implementation(projects.majesticHugeiconsBulk)
            implementation(projects.majesticHugeiconsDuotone)
            implementation(projects.majesticHugeiconsTwotone)
        }
    }
}