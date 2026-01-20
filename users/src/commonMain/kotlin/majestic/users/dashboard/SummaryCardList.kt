package majestic.users.dashboard

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.users.dashboard.tools.UserDetailsStatus
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class Summary(
    val label: String,
    val value: Int,
    val percentage: Float,
    val userDetailsStatus: UserDetailsStatus? = null,
    val summaryStatus: SummaryStatus? = null,
    val icon: DrawableResource,
)

data class SummaryCardListProps(
    val summaryCardProps: SummaryCardProps,
    val summaryList: List<Summary>,
    val orientation: ScreenOrientation
)

@Composable
internal fun SummaryCardList(
    props: SummaryCardListProps,
    modifier: Modifier = Modifier,
) {
    val orientation = props.orientation

    if (orientation == Landscape) Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        props.summaryList.forEach {
            SummaryCard(
                label = it.label,
                value = it.value,
                percentage = it.percentage,
                userDetailsStatus = it.userDetailsStatus,
                summaryStatus = it.summaryStatus,
                icon = painterResource(it.icon),
                props = props.summaryCardProps,
                orientation = orientation
            )
        }
    } else {
        val summary = props.summaryList.filterIndexed { index, _ -> index != 3 }
        LazyVerticalGrid(
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(summary.size) { index ->
                val item = summary[index]
                SummaryCard(
                    label = item.label,
                    value = item.value,
                    height = 110.dp,
                    percentage = item.percentage,
                    userDetailsStatus = item.userDetailsStatus,
                    summaryStatus = item.summaryStatus,
                    icon = painterResource(item.icon),
                    props = props.summaryCardProps,
                    orientation = orientation
                )
            }
        }
    }
}