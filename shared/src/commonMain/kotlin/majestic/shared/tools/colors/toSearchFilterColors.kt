package majestic.shared.tools.colors

import androidx.compose.ui.graphics.Color
import composex.screen.orientation.ScreenOrientation
import majestic.Dark
import majestic.Light
import majestic.SearchDefaultColors
import majestic.ThemeColor
import majestic.editor.tools.StateColors
import majestic.search.SearchFilterColors

internal fun ThemeColor.toSearchFilterColors() = SearchDefaultColors(
    hint = surface.contra.color.copy(alpha = 0.5f),
    text = surface.contra.color,
    cursor = dominant.actual.color,
    verticalDivider = Color.Transparent,
    tint = dominant.actual.color,
    border = StateColors(
        focused = dominant.actual.color,
        unfocused = surface.contra.color.copy(alpha = 0.1f)
    )
)

internal fun ThemeColor.toSearchFilterColors(orientation: ScreenOrientation): SearchFilterColors {
    val baseColor = surface.contra.color
    return when (this) {
        is Dark -> SearchFilterColors(
            background = barColors(orientation).background,
            text = baseColor.copy(alpha = 0.8f),
            hint = baseColor.copy(alpha = 0.4f),
            border = baseColor.copy(alpha = 0.1f),
            icon = baseColor.copy(alpha = 0.8f)
        )

        is Light -> SearchFilterColors(
            background = barColors(orientation).background,
            text = baseColor.copy(alpha = 0.8f),
            hint = baseColor.copy(alpha = 0.4f),
            border = baseColor.copy(alpha = 0.12f),
            icon = baseColor.copy(alpha = 0.8f)
        )
    }
}
