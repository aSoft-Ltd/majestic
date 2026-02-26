package majestic.shared.profiles.roles.details.header

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.unit.dp
import majestic.icons.Res
import majestic.icons.ic_add
import majestic.shared.profiles.roles.assign.form.ActionColors
import majestic.shared.profiles.roles.assign.form.SearchColors
import majestic.shared.profiles.roles.assign.tools.AssignmentController
import majestic.shared.profiles.roles.tools.SearchWrapper
import org.jetbrains.compose.resources.painterResource

data class TopHeaderColors(
    val background: Color,
    val counter: CounterColors,
    val separator: Color,
    val icon: ActionColors,
    val search: SearchColors,
)

data class RolesHeaderLabels(val search: String, val roles: String)

@Composable
internal fun TopHeader(
    modifier: Modifier,
    colors: TopHeaderColors,
    count: Int,
    labels: RolesHeaderLabels,
    controller: AssignmentController
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Counter(
        modifier = Modifier.wrapContentSize(),
        colors = colors.counter,
        count = count,
        labels = labels
    )

    SearchWrapper(
        modifier = Modifier
            .width(250.dp)
            .height(36.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                color = colors.search.searchField,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 16.dp),
        colors = colors.search,
        controller = controller,
        labels = labels
    )
    val interaction = remember { MutableInteractionSource() }
    val hovered by interaction.collectIsHoveredAsState()
    Icon(
        modifier = Modifier
            .clip(CircleShape)
            .pointerHoverIcon(icon = PointerIcon.Hand)
            .hoverable(interactionSource = interaction)
            .background(
                when (hovered) {
                    true -> colors.icon.background.focused
                    false -> colors.icon.background.unfocused
                }, CircleShape
            )
            .padding(16.dp)
            .size(20.dp),
        painter = painterResource(Res.drawable.ic_add),
        contentDescription = null,
        tint = colors.icon.foreground.focused
    )
}