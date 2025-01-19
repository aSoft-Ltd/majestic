package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

data class TableColors(
    val selected: Color = Color(0xFF152536),
    val hovered: Color = Color(0xFF1D2530),
    val inactive: Color = Color(0xFF1A212A),
    val separator: Color = Color(0xFF292D36)
)

@Composable
fun Table(
    data: List<List<@Composable (() -> Unit)>>,
    headers: List<@Composable (() -> Unit)>? = null,
    title: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    cellModifier: Modifier = Modifier,
    rowModifier: Modifier = Modifier,
    colors: TableColors = TableColors(),
) {
    Column(modifier = modifier) {
        // table title
        if (title != null) {
            Row(modifier = rowModifier.fillMaxWidth().background(colors.inactive)) { title() }
            HorizontalDivider(color = colors.separator)
        }

        // table header
        if (headers != null) {
            Row(modifier = rowModifier.background(colors.inactive)) {
                headers.forEach { cell -> Cell(modifier = cellModifier, content = cell, weight = 1f) }
            }
            HorizontalDivider(color = colors.separator)
        }

        // table data
        LazyColumn {
            data.forEachIndexed { index, row ->
                item {
                    val interactionSource = remember { MutableInteractionSource() }
                    val isHovered by interactionSource.collectIsHoveredAsState()
                    val rowColor = if (isHovered) colors.hovered else colors.inactive

                    Row(
                        modifier = rowModifier
                            .hoverable(interactionSource = interactionSource)
                            .background(rowColor)
                    ) {
                        row.forEach { cell -> Cell(modifier = cellModifier, content = cell, weight = 1f) }
                    }
                    if (index != data.lastIndex) {
                        HorizontalDivider(color = colors.separator)
                    }
                }
            }
        }
    }
}

@Composable
private fun RowScope.Cell(modifier: Modifier, content: @Composable () -> Unit, weight: Float) {
    Box(modifier = modifier.weight(weight)) { content() }
}
