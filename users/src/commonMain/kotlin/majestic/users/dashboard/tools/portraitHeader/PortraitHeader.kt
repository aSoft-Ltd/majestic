package majestic.users.dashboard.tools.portraitHeader

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import majestic.buttons.ActionButton
import majestic.icons.Res
import majestic.icons.ic_add
import majestic.shared.users.dashboard.PortraitHeaderColors
import majestic.shared.users.label.dashboard.DashboardLabels
import majestic.tooling.onClick
import majestic.users.dashboard.tools.ActiveView
import majestic.users.dashboard.tools.View
import org.jetbrains.compose.resources.vectorResource


@Composable
internal fun PortraitHeader(
    modifier: Modifier,
    colors: PortraitHeaderColors,
    labels: DashboardLabels,
    navigator: ActiveView,
    add: (view: View) -> Unit,
    manage: (view: View) -> Unit
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    val add = MutableInteractionSource()
    val hovered by add.collectIsHoveredAsState()
    TabsBox(
        labels = labels.tabs,
        colors = colors.tab,
        navigator = navigator,
        modifier = Modifier
    )
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ActionButton(
            modifier = Modifier.padding(vertical = 3.dp),
            colors = colors.manage,
            text = labels.roles.manage,
            onClick = {
                manage(navigator.view)
            },
            shape = RoundedCornerShape(4.dp)
        )
        Icon(
            modifier = Modifier
                .onClick { add(navigator.view) }
                .clip(RoundedCornerShape(4.dp))
                .background(
                    shape = RoundedCornerShape(4.dp),
                    color = if (hovered) colors.add.copy(.8f) else colors.add.copy(.7f)
                )
                .padding(8.dp)
                .size(20.dp),
            imageVector = vectorResource(Res.drawable.ic_add),
            contentDescription = "Add Icon",
        )
    }
}