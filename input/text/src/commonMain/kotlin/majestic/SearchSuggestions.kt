package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import majestic.colors.ThemeColors

@Composable
fun <T> SearchSuggestions(
    expanded: Boolean,
    suggestions: List<T>,
    containerWidth: Int,
    theme: ThemeColors,
    onItemSelected: (T) -> Unit,
    onNavigate: (T) -> Unit,
    suggestion: @Composable (T) -> Unit,
    maxHeight: Dp = 350.dp
) {
    val density = LocalDensity.current
    DropdownMenu(
        containerColor = theme.surface1.main.background,
        expanded = expanded,
        onDismissRequest = { /* Do nothing to prevent auto-dismiss */ },
        modifier = Modifier.width(with(density) { containerWidth.toDp() })
            .background(theme.surface1.main.background)
            .requiredSizeIn(maxHeight = maxHeight),
        properties = PopupProperties(
            focusable = false,
            dismissOnBackPress = false,
            dismissOnClickOutside = true
        )
    ) {
        suggestions.forEachIndexed { index, item ->
            DropdownMenuItem(
                modifier = Modifier.width(with(density) { containerWidth.toDp() })
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

