package majestic.shared.dashboards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import composex.screen.orientation.ScreenOrientation
import majestic.icons.Res
import majestic.icons.ic_arrow_down_01_solid
import majestic.icons.ic_arrow_up_02


enum class SummaryStatus { GOOD, BAD }

@Composable
fun SummaryCard(
    label: String,
    value: Int,
    icon: Painter,
    orientation: ScreenOrientation,
    percentage: Float? = null,
    summaryStatus: SummaryStatus? = null,
    graphColor: Color,
    modifier: Modifier,
    props: SummaryCardColorProps,
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.SpaceBetween
) {
    val summaryColor = when (summaryStatus) {
        SummaryStatus.GOOD -> Color(0xFF46C362)
        else -> Color(0xFFF44336)
    }
    val summaryIcon = when (summaryStatus) {
        SummaryStatus.GOOD -> Res.drawable.ic_arrow_up_02
        else -> Res.drawable.ic_arrow_down_01_solid
    }
    SummaryCardTop(
        modifier = Modifier.fillMaxWidth(),
        props = props,
        orientation = orientation,
        icon = icon,
        percentage = percentage,
        summaryColor = summaryColor,
        summaryIcon = summaryIcon
    )
    SummaryCardBottom(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        props = props,
        orientation = orientation,
        label = label,
        graphColor = graphColor
    )
}