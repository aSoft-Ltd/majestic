package majestic.drawer

import androidx.compose.ui.unit.Dp

internal sealed interface DrawerSpan

internal data class DpSpan(val value: Dp) : DrawerSpan

internal data class RatioSpan(val value: Float) : DrawerSpan