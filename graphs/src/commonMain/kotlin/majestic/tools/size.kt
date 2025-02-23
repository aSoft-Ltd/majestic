package majestic.tools

import androidx.compose.ui.geometry.Size

inline operator fun Size.minus(other: Size) = Size(width - other.width, height - other.height)