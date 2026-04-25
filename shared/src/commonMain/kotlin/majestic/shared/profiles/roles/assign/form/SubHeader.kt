package majestic.shared.profiles.roles.assign.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.Search
import majestic.editor.tools.StateColors
import majestic.icons.Res
import majestic.icons.ic_account_setting
import majestic.icons.ic_search
import majestic.shared.profiles.roles.assign.tools.AssignmentController
import majestic.shared.profiles.roles.data.RoleAssignmentLabels
import majestic.shared.tools.modal.ModalColors
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource

data class ActionColors(
    val foreground: StateColors,
    val background: StateColors
)

@Composable
internal fun SubHeader(
    modifier: Modifier,
    colors: ModalColors,
    orientation: ScreenOrientation,
    controller: AssignmentController,
    labels: RoleAssignmentLabels
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_account_setting),
            contentDescription = null,
            tint = colors.text,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "${controller.selectedRoles.size}/${controller.allRoles.size} ${labels.selected}",
            color = colors.text,
            fontSize = 14.sp
        )
    }

    when (orientation) {
        is Landscape -> Search(
            modifier = Modifier
                .width(250.dp)
                .height(36.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = colors.search.background, shape = RoundedCornerShape(20.dp))
                .padding(start = 16.dp),
            value = controller.searchQuery,
            onChange = { controller.searchQuery = it },
            hint = labels.search,
            colors = colors.search,
        )

        is Portrait -> Icon(
            imageVector = vectorResource(Res.drawable.ic_search),
            tint = colors.text.copy(0.6f),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
    }
}