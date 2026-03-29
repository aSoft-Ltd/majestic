package majestic.shared.tools

import androidx.compose.ui.graphics.Color

data class CommonProfileColors(
    val header: Color,
    val headerIcons: Color,
    val headerText: Color,
    val foreground: Color,
    val dominantActual: Color,
    val iconButton: Color,
    val tab: Color,
    val body: Color,
    val innerBodyContainer: Color,
    val listItemRow: ListItemRow,
    val conversation: Conversation
)

data class ListItemRow(
    val background: Color,
    val innerContentContainer: Color,
    val innerContentBorder: Color,
    val border: Color,
    val iconBg: Color,
    val iconTint: Color,
    val tagText: Color,
    val tagBg: Color,
    val title: Color,
    val subtitle: Color,
)

data class Conversation(
    val active: Color,
    val bubble: Color,
    val separator: Color,
    val bodyText: Color,
    val input: Color,
    val inputBorder: Color,
)