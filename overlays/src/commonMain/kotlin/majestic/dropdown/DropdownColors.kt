package majestic.dropdown

import androidx.compose.ui.graphics.Color

data class DropdownColors(
    val trigger: Color,
    val triggerText: Color = trigger,
    val dropdown: Color,
    val itemsDefault: Color,
    val itemsSelected: Color,
)