pluginManagement {
    includeBuild("../build-logic")
}

plugins {
    id("multimodule")
}

fun includeSubs(base: String, path: String = base, vararg subs: String) {
    subs.forEach {
        include(":$base-$it")
        project(":$base-$it").projectDir = File("$path/$it")
    }
}

rootProject.name = "majestic"

includeSubs("majestic", ".", "theme", "table", "graphs", "drawers", "screen", "loaders")
includeSubs("majestic-input", "input", "core", "text", "color", "choice", "phone")
