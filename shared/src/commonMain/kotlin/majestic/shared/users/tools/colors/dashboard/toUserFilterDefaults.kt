package majestic.shared.users.tools.colors.dashboard

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp
import majestic.ColorPair
import majestic.Dark
import majestic.Light
import majestic.ThemeColor
import majestic.filters.FilterDefault
import majestic.filters.SelectFilterColors
import majestic.shared.tools.colors.toBackground

private fun ThemeColor.toFilterColors(
    yearFilterSelected: Boolean,
    isHovered: Boolean,
) = SelectFilterColors(
    background = when {
        yearFilterSelected || isHovered -> surface.contra.color.copy(.1f)
        else -> surface.contra.color.copy(.05f)
    },
    text = when {
        yearFilterSelected || isHovered -> surface.contra.color
        else -> surface.contra.color.copy(.5f)
    },
    icon = surface.contra.color,
)

private fun ThemeColor.toColorListItem() = when (this) {
    is Light -> ColorPair(dominant.contra.color.copy(.7f), dominant.contra.color)
    is Dark -> ColorPair(surface.contra.color.copy(.7f), surface.contra.color)
}

fun ThemeColor.toUserFilterDefaults(
    yearFilterSelected: Boolean,
    isHovered: Boolean
) = FilterDefault(
    drawerColor = if (this is Light) dominant.actual.color else toBackground,
    colors = toFilterColors(yearFilterSelected, isHovered),
    containerShape = RoundedCornerShape(8.dp),
    drawerShape = RoundedCornerShape(8.dp),
    item = toColorListItem()
)
