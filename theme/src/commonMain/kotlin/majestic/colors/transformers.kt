package majestic.colors

import androidx.compose.ui.graphics.Color

fun Color.toColorPair() = ColorPair(this, this)

fun ColorPair.toColorRole() = ColorRole(this, this)

fun Color.toColorRole() = toColorPair().toColorRole()