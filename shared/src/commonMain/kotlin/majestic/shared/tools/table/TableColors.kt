package majestic.shared.tools.table

import androidx.compose.ui.graphics.Color

data class TableColors(
    val header: Color,
    val body: Color,
    val headerBorder: Color,
    val bodyBorder: Color,
    val hovered: Color,
    val selected: Color,
    val selectedHovered: Color
)

data class MobileTableColors(
    val header: Color,
    val headerBorder: Color,
    val subHeader: Color,
    val subHeaderBorder: Color,
    val body: Color,
    val itemsSeparator: Color,
)