package majestic.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation

@Composable
fun Flex(
    modifier: Modifier = Modifier,
    orientation: ScreenOrientation,
    col: FlexCol = FlexCol(),
    row: FlexRow = FlexRow(),
    content: @Composable FlexScope.() -> Unit
) {
    when (orientation) {
        Landscape -> Row(
            modifier = modifier,
            horizontalArrangement = row.arrangement,
            verticalAlignment = row.alignment
        ) {
            FlexScope.Row(this).content()
        }

        Portrait -> Column(
            modifier = modifier,
            verticalArrangement = col.arrangement,
            horizontalAlignment = col.alignment
        ) {
            FlexScope.Column(this).content()
        }
    }
}

@Composable
fun Flex(
    modifier: Modifier = Modifier,
    orientation: ScreenOrientation,
    col: FlexCol = FlexCol(),
    row: FlexRow = FlexRow(),
    reverse: Boolean,
    content: @Composable FlexScope.() -> Unit
) {
    if (reverse) when (orientation) {
        Portrait -> Row(
            modifier = modifier,
            horizontalArrangement = row.arrangement,
            verticalAlignment = row.alignment
        ) {
            FlexScope.Row(this).content()
        }

        Landscape -> Column(
            modifier = modifier,
            verticalArrangement = col.arrangement,
            horizontalAlignment = col.alignment
        ) {
            FlexScope.Column(this).content()
        }
    } else Flex(modifier, orientation, col, row, content)
}

@Deprecated("Use the version that takes FlexCol and FlexRow parameters")
@Composable
fun Flex(
    modifier: Modifier = Modifier,
    orientation: ScreenOrientation,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(20.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(40.dp),
    alignment: Alignment.Vertical = Alignment.Top,
    content: @Composable FlexScope.() -> Unit
) {
    when (orientation) {
        Landscape -> Row(
            modifier = modifier,
            horizontalArrangement = horizontalArrangement,
            verticalAlignment = alignment
        ) {
            FlexScope.Row(this).content()
        }

        Portrait -> Column(
            modifier = modifier,
            verticalArrangement = verticalArrangement
        ) {
            FlexScope.Column(this).content()
        }
    }
}
