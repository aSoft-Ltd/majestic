package majestic.users.profile

import academia.generated.resources.Res
import academia.generated.resources.ic_add
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import captain.Navigator
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.Dark
import majestic.ExpandDirection
import majestic.FloatingActionButton
import majestic.ThemeColor
import majestic.tooling.onClick
import majestic.users.profile.tools.ProfileDestinationMapper
import majestic.users.profile.tools.colors.barColors
import majestic.users.tools.data.UsersData
import org.jetbrains.compose.resources.vectorResource
import tz.co.asoft.academia.admissions.dashboard.bulk.review.enrolled.tools.EnrolledData
import tz.co.asoft.academia.admissions.dashboard.bulk.review.tools.Bar
import tz.co.asoft.academia.admissions.dashboard.bulk.review.tools.BarColors
import tz.co.asoft.academia.admissions.dashboard.bulk.review.tools.MainColors
import tz.co.asoft.academia.admissions.dashboard.bulk.review.tools.recall.Form
import tz.co.asoft.academia.profile.school.tools.modalBackgroundColors
import tz.co.asoft.academia.profile.school.tools.schoolHeadersBackgroundColor
import tz.co.asoft.academia.tools.ActionBar
import tz.co.asoft.academia.tools.AutoHideTopBarEffect
import tz.co.asoft.academia.tools.colors.background
import tz.co.asoft.academia.tools.colors.barColors
import tz.co.asoft.academia.tools.colors.toFilterColors
import tz.co.asoft.academia.tools.dialogs.FlexibleDialog
import tz.co.asoft.academia.tools.filters.FilterByYear

@Composable
internal fun EnrolledDetail(
    orientation: ScreenOrientation,
    users: UsersData,
    theme: ThemeColor,
    endpoint: ProfileDestinationMapper,
    navigator: Navigator,
    labels: ProfileHeaderLabels,
    modifier: Modifier = Modifier,
    content: @Composable (() -> Unit) = {},
) = Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

    val barColors = theme.barColors(orientation)
    var recall by remember { mutableStateOf(false) }
    AutoHideTopBarEffect(orientation = orientation, bar = panel.bar)
    if (recall) FlexibleDialog(
        theme = theme,
        onDismiss = { recall = false },
        title = "",
        modifier = if (orientation is Landscape) Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = theme.schoolHeadersBackgroundColor)
            .size(700.dp, 800.dp)
        else Modifier
            .fillMaxSize()
            .background(theme.modalBackgroundColors),
        orientation = orientation,
        bar = {
            Bar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .padding(horizontal = if (orientation is Landscape) 20.dp else 10.dp, vertical = 8.dp),
                title = enrolled.title,
                subtitle = enrolled.campus,
                colors = BarColors(
                    main = MainColors(
                        text = theme.surface.contra.color,
                        tint = theme.surface.contra.color,
                        background = theme.background
                    ),
                    closing = ColorPair(
                        background = theme.surface.contra.color.copy(.5f),
                        foreground = theme.surface.contra.color
                    )
                ),
                onDismiss = { recall = false },
                icon = enrolled.campusIcon,
                orientation = orientation,
                campusIcon = enrolled.iconResource,
            )
        },
    ) {
        Form(
            modifier = Modifier.fillMaxSize(),
            data = enrolledData(
                data = EnrolledData.random(),
                onSubmit = { recall = false },
                onCancel = { recall = false }
            ),
            theme = theme,
            orientation = orientation
        )
    }

    if (orientation is Landscape) ActionBar(
        modifier = Modifier.height(70.dp),
        panel = panel
    ) { _, colors ->
        FilterByYear(
            years = listOf("2025", "2024", "2023"),
            selected = "2025",
            modifier = Modifier.width(110.dp),
            defaults = colors.toFilterColors()
        )
    }

    Column(
        modifier = Modifier.fillMaxHeight()
            .fillMaxWidth(if (orientation is Landscape) .85f else 1f)
            .then(if (orientation is Landscape) Modifier.padding(20.dp) else Modifier),
        verticalArrangement = Arrangement.spacedBy(if (orientation is Landscape) 10.dp else 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DetailHeader(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(RoundedCornerShape(if (orientation is Landscape) 20.dp else 0.dp))
                .background(if (orientation is Landscape) theme.background.copy(.5f) else barColors.background),
            user = enrolled,
            labels = labels,
            theme = theme,
            orientation = orientation,
            navigator = navigator,
            endpoint = endpoint,
            back = backIcon,
        ) {
            recall = true
        }
        Box(modifier = Modifier.fillMaxSize()) {
            content()

            if (orientation is Portrait) Box(
                modifier = Modifier.wrapContentSize().align(Alignment.BottomEnd).padding(bottom = 30.dp, end = 20.dp)
            ) {
                FloatingActionButton(
                    expanded = false,
                    direction = ExpandDirection.UP,
                    color = ColorPair(
                        background = if (theme is Dark) theme.surface.contra.color else theme.dominant.actual.color,
                        foreground = theme.surface.actual.color
                    ),
                    modifier = Modifier
                        .onClick({
                            recall = true
                        })
                        .size(52.dp)
                        .background(
                            if (theme is Dark) theme.surface.contra.color else theme.dominant.actual.color,
                            shape = CircleShape
                        ),
                    label = {
                        Icon(
                            imageVector = vectorResource(Res.drawable.ic_add),
                            contentDescription = null,
                            tint = theme.surface.actual.color
                        )
                    }
                ) { }
            }
        }
    }
}