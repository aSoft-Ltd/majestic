package majestic.users.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.ThemeColor
import majestic.editor.toolbar.underline
import majestic.users.dashboard.stickybar.StickyBar
import majestic.users.dashboard.stickybar.filters.FilterDefault
import majestic.users.dashboard.summary.SummaryCardColorProps
import majestic.users.dashboard.summary.SummaryCardList
import majestic.users.dashboard.summary.SummaryCardListProps
import majestic.users.dashboard.summary.toSummary
import majestic.users.labels.UsersLabels

data class UserDashboardProps(
    val filterDefaults: FilterDefault,
    val view: TableViewProps,
    val background: Color
)

@Composable
fun UsersDashboard(
    modifier: Modifier,
    props: UserDashboardProps,
    orientation: ScreenOrientation,
    theme: ThemeColor,
    labels: UsersLabels,
    onItemClick: () -> Unit = {}
) = Column(modifier = modifier) {
    if (orientation is Landscape) StickyBar(
        theme = theme,
        modifier = Modifier
            .underline(theme.surface.contra.color.copy(alpha = 0.05f))
            .fillMaxWidth()
            .wrapContentHeight()
            .height(IntrinsicSize.Max)
            .padding(horizontal = 16.dp, vertical = 16.dp),
        title = labels.dashboard.insights.title,
        detail = labels.dashboard.insights.detail,
        orientation = orientation,
        filterDefaults = props.filterDefaults,
        selected = "2025"
    )
    Column(
        modifier = Modifier.padding(
            horizontal = when (orientation) {
                Landscape -> 20.dp
                Portrait -> 10.dp
            }
        )
    ) {
        SummaryCardList(
            modifier = Modifier.padding(top = 20.dp, bottom = 10.dp),
            props = SummaryCardListProps(
                summaryCardProps = SummaryCardColorProps(
                    colors = ColorPair(
                        foreground = theme.surface.contra.color,
                        background = theme.surface.actual.color
                    )

                ),
                summaryList = labels.dashboard.summary.toSummary(),
                orientation = orientation
            ),
        )
        when (orientation) {
            is Landscape -> LandscapeView(
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp).fillMaxSize(),
                orientation = orientation,
                props = props.view,
                labels = labels,
                onItemClick = onItemClick
            )

            is Portrait -> {
                PortraitView(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(props.background.copy(.5f))
                        .padding(bottom = 10.dp),
                    orientation = orientation,
                    props = props.view,
                    labels = labels,
                    onItemClick = {},
                    manage = {}
                )
            }
        }
    }
}