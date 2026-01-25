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
@Deprecated("There is a new version, waiting to be extracted")
@Composable
fun Flex(
    modifier: Modifier = Modifier,
    orientation: ScreenOrientation,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(10.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
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
