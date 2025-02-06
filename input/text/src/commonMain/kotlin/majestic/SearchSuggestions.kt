package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.window.PopupProperties
import majestic.colors.ThemeColors

@Composable
fun <T> SearchSuggestions(
    expanded: Boolean,
    suggestions: List<T>,
    theme: ThemeColors,
    onItemSelected: (T) -> Unit,
    onNavigate: (T) -> Unit,
    suggestion: @Composable (T) -> Unit,
    modifier: Modifier
) {
    val density = LocalDensity.current
    DropdownMenu(
        containerColor = theme.surface1.main.background,
        expanded = expanded,
        onDismissRequest = { /* Do nothing to prevent auto-dismiss */ },
        modifier = modifier,
        properties = PopupProperties(
            focusable = false,
            dismissOnBackPress = false,
            dismissOnClickOutside = true
        )
    ) {
        suggestions.forEachIndexed { index, item ->
            DropdownMenuItem(
                modifier = Modifier.fillMaxWidth()
                    .background(theme.surface1.main.background),
                onClick = {
                    onItemSelected(item)
                    onNavigate(item)
                },
                text = {
                    suggestion(item)
                }
            )
        }
    }
}

