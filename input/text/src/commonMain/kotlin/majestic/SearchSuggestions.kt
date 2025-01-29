package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import majestic.colors.ThemeColors

@Composable
fun <T> SearchSuggestions(
    expanded: Boolean,
    suggestions: List<T>,
    containerWidth: Int,
    theme: ThemeColors,
    itemText: (T) -> String,
    itemType: (T) -> String,
    onItemSelected: (T) -> Unit,
    onNavigate: (T) -> Unit,
) {
    val density = LocalDensity.current

    DropdownMenu(
        containerColor = theme.surface1.main.background,
        expanded = expanded,
        onDismissRequest = { /* Do nothing to prevent auto-dismiss */ },
        modifier = Modifier
            .width(with(density) { containerWidth.toDp() })
            .background(theme.surface1.main.background),
        properties = PopupProperties(
            focusable = false,
            dismissOnBackPress = false,
            dismissOnClickOutside = true
        )
    ) {
        suggestions.forEach { item ->
            DropdownMenuItem(
                modifier = Modifier
                    .width(with(density) { containerWidth.toDp() })
                    .background(theme.surface1.main.background),
                onClick = {
                    onItemSelected(item)
                    onNavigate(item)
                },
                text = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = itemText(item),
                            color = theme.surface1.main.foreground
                        )
                        Text(
                            text = itemType(item),
                            color = theme.surface1.main.foreground.copy(alpha = 0.6f),
                            fontSize = 14.sp
                        )
                    }
                }
            )
        }
    }
}