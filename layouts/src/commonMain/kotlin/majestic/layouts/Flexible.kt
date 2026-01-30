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

@Deprecated("Use Flex instead")
@Composable
fun Flexible(
    modifier: Modifier = Modifier,
    orientation: ScreenOrientation,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(20.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.End,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
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
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        ) {
            FlexScope.Column(this).content()
        }
    }
}
