package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.rememberScreenOrientation
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
    val itemHeights = remember { mutableStateMapOf<Int, Int>() }
    val orientation = rememberScreenOrientation()
    val baseHeight = if (orientation is Landscape) 350.dp else 250.dp
    val maxHeight = remember(itemHeights.toMap(), suggestions) {
        if (itemHeights.keys.toSet() != suggestions.indices.toSet()) {
            return@remember baseHeight
        }
        val baseHeightInt = with(density) { baseHeight.toPx().toInt() }
        with(density) { DropdownMenuVerticalPadding.toPx().toInt() } * 2

        var sum = with(density) { DropdownMenuVerticalPadding.toPx().toInt() } * 2
        for (entry in itemHeights.entries.sortedBy { it.key }) {
            sum += entry.value
            if (sum >= baseHeightInt) {
                return@remember with(density) { (sum - entry.value / 2).toInt().toDp() }
            }
        }
        baseHeight
    }

    DropdownMenu(
        containerColor = theme.surface1.main.background,
        expanded = expanded,
        onDismissRequest = { /* Do nothing to prevent auto-dismiss */ },
        modifier = Modifier
            .then(
                other = when (orientation) {
                    Landscape -> {
                        Modifier.width(with(density) { containerWidth.toDp() })
                    }

                    Portrait -> {
                        Modifier.fillMaxWidth(.9f)
                    }
                }
            )
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
                modifier = Modifier
                    .then(
                        other = when (orientation) {
                            Landscape -> {
                                Modifier.width(with(density) { containerWidth.toDp() })
                            }

                            Portrait -> {
                                Modifier.fillMaxWidth()
                            }
                        }
                    )
                    .background(theme.surface1.main.background)
                    .onSizeChanged {
                        itemHeights[index] = it.height
                    },
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

private val DropdownMenuVerticalPadding = 8.dp