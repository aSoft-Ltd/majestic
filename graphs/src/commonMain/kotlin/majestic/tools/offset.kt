package majestic.tools

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size

internal inline fun Size.toOffset() = Offset(width, height)