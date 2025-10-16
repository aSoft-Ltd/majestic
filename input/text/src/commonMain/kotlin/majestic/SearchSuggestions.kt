package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.PopupProperties

data class SuggestionColors(
    val container: Color = Color.Black,
    val suggestions: SuggestionHighlightColors = SuggestionHighlightColors(
        active = Color.White,
        inactive = Color.Black
    )
) {
    companion object {
        val default by lazy {
            SuggestionColors(
                Color.Black,
                SuggestionHighlightColors(active = Color.White, inactive = Color.Black)
            )
        }
    }
}

data class SuggestionHighlightColors(
    val inactive: Color,
    val active: Color,
)

@Composable
fun <T> SearchSuggestions(
    expanded: Boolean,
    suggestions: List<T>,
    currentSelectedIndex: Int,
    colors: SuggestionColors,
    onClick: (T) -> Unit,
    suggestion: @Composable (T) -> Unit,
    modifier: Modifier = Modifier
) {
    DropdownMenu(
        containerColor = colors.container,
        expanded = expanded,
        onDismissRequest = { /* Do nothing to prevent auto-dismiss */ },
        modifier = modifier,
        properties = PopupProperties(
            focusable = false,
            dismissOnBackPress = false,
            dismissOnClickOutside = true
        ),
    ) {
        suggestions.forEachIndexed { index, item ->
            DropdownMenuItem(
                modifier = Modifier.fillMaxWidth()
                    .background(
                        if (index == currentSelectedIndex)
                            colors.suggestions.active else colors.suggestions.inactive
                    ),
                onClick = { onClick(item) },
                text = { suggestion(item) },
            )
        }
    }
}