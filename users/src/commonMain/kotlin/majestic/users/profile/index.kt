package majestic.users.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import captain.Route
import captain.RoutesBuilder
import composex.screen.orientation.Landscape
import tz.co.asoft.academia.admissions.dashboard.bulk.review.tools.criteria.Criteria
import tz.co.asoft.academia.admissions.opportunity.detail.interview.candidates.Candidates
import tz.co.asoft.academia.tools.colors.background
import tz.co.asoft.academia.tools.panel.controller.PanelController

internal fun RoutesBuilder.enrolledRoutes(panel: PanelController) {
    val path = panel.scope.endpoint.admissions.enrolled.isolated()
    val theme by panel.thm.state
    val labels = panel.scope.settings.translator.value.labels.admission.bulk.criteria
    val orientation = panel.orientation.value
    Route(path.index()) {
        Criteria(
            theme = theme,
            labels = labels,
            orientation = orientation,
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .then(if (orientation is Landscape) Modifier.wrapContentHeight() else Modifier.fillMaxHeight())
                .background(
                    color = if (orientation is Landscape) theme.background else Color.Transparent,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(20.dp)
        )
    }

    Route(path.candidates()) {
        Candidates(
            panel = panel,
            modifier = Modifier.clip(RoundedCornerShape(20.dp)).fillMaxSize(),
            showSearch = false
        )
    }
}