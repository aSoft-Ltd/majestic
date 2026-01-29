package majestic.shared.button

import androidx.compose.ui.graphics.vector.ImageVector

data class ButtonIconOld(
    val resource: ImageVector,
    val contentDescription: String? = null,
    val rotation: Float = 0f
)