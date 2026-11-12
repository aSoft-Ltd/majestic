package majestic.shared.tools.selectableChip

import androidx.compose.ui.graphics.Color

data class SelectableChipStateColors(
    val text: Color,
    val background: Color,
    val indicator: Color,
    val border: Color = Color.Transparent
)

data class SelectableChipColors(
    val default: SelectableChipStateColors,
    val hovered: SelectableChipStateColors,
    val selected: SelectableChipStateColors
)