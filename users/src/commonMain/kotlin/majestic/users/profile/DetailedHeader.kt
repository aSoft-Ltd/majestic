package majestic.users.profile

import academia.admission.bulk.EnrollDestinations
import academia.admission.enroll.table.BulkLabels
import academia.generated.resources.Res
import academia.generated.resources.ic_arrow_left
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import captain.Navigator
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ActionButton
import majestic.Light
import majestic.ThemeColor
import majestic.tooling.onClick
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.academia.admissions.dashboard.bulk.review.enrolled.tools.EnrolledData
import tz.co.asoft.academia.admissions.dashboard.bulk.review.tools.header.Head
import tz.co.asoft.academia.admissions.dashboard.bulk.review.tools.header.HeadData
import tz.co.asoft.academia.admissions.dashboard.bulk.review.tools.header.Status
import tz.co.asoft.academia.blog.editor.topbar.toolbar.tabs.home.prompt.tools.toSubmitColors
import tz.co.asoft.academia.tools.colors.background

@Composable
internal fun DetailHeader(
    labels: BulkLabels,
    enrolled: EnrolledData,
    theme: ThemeColor,
    navigator: Navigator,
    endpoint: EnrollDestinations,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier,
    onActionClick: () -> Unit = {}
) = Column(
    modifier = modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(if (orientation is Landscape) 20.dp else 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.weight(1f).wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            if (orientation is Portrait) Icon(
                modifier = Modifier.size(24.dp).onClick { navigator.go(-1) },
                painter = painterResource(Res.drawable.ic_arrow_left),
                tint = if (theme.mode is Light) theme.dominant.contra.color else theme.surface.contra.color,
                contentDescription = "Icon"
            )
            Head(
                modifier = Modifier.height(IntrinsicSize.Max),
                data = HeadData(
                    title = enrolled.title,
                    status = Status(
                        label = enrolled.status.getLabel(labels.table.enrolled.body.status),
                        color = enrolled.status.getColor(theme)
                    ),
                    id = Pair(labels.header.header.enrollmentNumber, enrolled.id),
                    dateTime = Pair(
                        labels.header.header.dateTime,
                        "${enrolled.datePerformed.dropLast(5)} - ${enrolled.timePerformed}"
                    ),
                    performedBy = Pair(labels.header.header.performedBy, enrolled.performedBy),
                    count = Pair(labels.header.header.enrolledCount, enrolled.enrolledCount)
                ),
                theme = theme,
                orientation = orientation,
            )
        }

        if (orientation is Landscape) ActionButton(
            modifier = Modifier.width(100.dp).height(40.dp),
            colors = theme.toSubmitColors(),
            text = labels.header.header.recall,
            onClick = onActionClick
        )
    }
    Tabs(
        modifier = Modifier
            .height(if (orientation is Landscape) 50.dp else 40.dp)
            .fillMaxWidth()
            .background(color = theme.background)
            .padding(horizontal = if (orientation is Landscape) 30.dp else 10.dp)
            .horizontalScroll(rememberScrollState()),
        labels = labels.header.tabs,
        themes = theme,
        navigator = navigator,
        endpoint = endpoint
    )
}