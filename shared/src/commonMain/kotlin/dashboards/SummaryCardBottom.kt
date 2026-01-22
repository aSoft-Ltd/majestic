package dashboards

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.tooling.animateAsState
import majestic.tools.graphVector

@Composable
internal fun SummaryCardBottom(
    modifier: Modifier,
    value: Int,
    props: SummaryCardColorProps,
    orientation: ScreenOrientation,
    label: String,
    graphColor: Color
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        Column {
            val v by animateAsState(to = value, steps = 50)
            Text(
                text = "${v.toInt()}",
                color = props.colors.foreground,
                fontSize = if (orientation is Landscape) 24.sp else 16.sp,
            )
            Text(
                text = label,
                fontSize = if (orientation is Landscape) 16.sp else 12.sp,
                color = props.colors.foreground.copy(0.5f)
            )
        }
        Image(
            modifier = if (orientation is Portrait) Modifier.width(50.dp) else Modifier,
            imageVector = graphVector(graphColor),
            contentDescription = null
        )
    }
}