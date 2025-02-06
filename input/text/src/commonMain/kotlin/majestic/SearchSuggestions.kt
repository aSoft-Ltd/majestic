package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.PopupProperties
import majestic.colors.ThemeColors

@Composable
fun <T> SearchSuggestions(
    expanded: Boolean,
    suggestions: List<T>,
    theme: ThemeColors,
    onClick: (T) -> Unit,
    suggestion: @Composable (T) -> Unit,
    modifier: Modifier = Modifier
) = DropdownMenu(
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
    for (item in suggestions) DropdownMenuItem(
        modifier = Modifier.fillMaxWidth().background(theme.surface1.main.background),
        onClick = { onClick(item) },
        text = { suggestion(item) }
    )
}

