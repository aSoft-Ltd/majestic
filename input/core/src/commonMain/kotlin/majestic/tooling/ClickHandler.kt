package majestic.tooling

import androidx.compose.foundation.Indication
import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import majestic.NoRippleInteractionSource

fun Modifier.onClick(callback: () -> Unit) = clickable(
    interactionSource = NoRippleInteractionSource,
    indication = null,
    enabled = true,
    onClick = callback
)

fun Modifier.onClick(indication: Indication? = null, enabled: Boolean = true, callback: () -> Unit) = clickable(
    interactionSource = NoRippleInteractionSource,
    indication = indication,
    enabled = enabled,
    onClick = callback
)
