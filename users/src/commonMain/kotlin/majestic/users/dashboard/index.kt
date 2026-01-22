package majestic.users.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import dashboards.stickybar.StickyBar
import dashboards.stickybar.filters.FilterDefault
import majestic.ColorPair
import majestic.ThemeColor
import majestic.editor.toolbar.underline
import majestic.users.dashboard.summary.SummaryCardList
import majestic.users.dashboard.tools.toSummaryCardProps
import users.UsersLabels

data class UserDashboardProps(
    val filterDefaults: FilterDefault,
    val view: TableViewProps,
    val background: Color,
    val summaryCard: ColorPair
)

@Composable
fun UsersDashboard(
    modifier: Modifier,
    props: UserDashboardProps,
    orientation: ScreenOrientation,
    theme: ThemeColor,
    labels: UsersLabels,
    onItemClick: () -> Unit = {},
    manageUsers: () -> Unit = {},
    manageRoles: () -> Unit = {},
    addUser: () -> Unit = {},
    addRole: () -> Unit = {},
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
            modifier = when (orientation) {
                is Landscape -> Modifier
                    .border(12.dp, Color.Red)
                    .padding(top = 20.dp, bottom = 10.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .horizontalScroll(rememberScrollState())

                is Portrait -> Modifier.padding(8.dp).fillMaxWidth().wrapContentSize()
            },
            props = labels.toSummaryCardProps(props, orientation),
        )
        when (orientation) {
            is Landscape -> LandscapeView(
                modifier = Modifier.padding(top = 10.dp, bottom = 20.dp).fillMaxSize(),
                orientation = orientation,
                props = props.view,
                labels = labels,
                onItemClick = onItemClick,
                manageUsers = manageUsers,
                manageRoles = manageRoles,
                addUser = addUser,
                addRole = addRole
            )

            is Portrait -> PortraitView(
                orientation = orientation,
                props = props.view,
                labels = labels,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(props.background.copy(.5f))
                    .padding(bottom = 10.dp),
                manageUsers = manageUsers,
                manageRoles = manageRoles,
                addUser = addUser,
                onItemClick = onItemClick,
                addRole = addRole,
            )
        }
    }
}