package majestic.shared.profiles.roles.assign.form

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.editor.tools.StateColors
import majestic.icons.Res
import majestic.icons.ic_account_setting
import majestic.icons.ic_cancel
import majestic.shared.profiles.roles.assign.tools.AssignmentController
import majestic.shared.profiles.roles.data.RoleAssignmentLabels
import majestic.tooling.onClick
import org.jetbrains.compose.resources.vectorResource

data class HeaderColors(
    val background: Color,
    val icon: Color,
    val title: Color,
    val iconBackground: Color,
    val cancel: Color,
    val cancelBackground: StateColors
)

@Composable
internal fun Header(
    modifier: Modifier,
    labels: RoleAssignmentLabels,
    controller: AssignmentController,
    colors: HeaderColors,
    onDismiss: () -> Unit
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Row(
        modifier = Modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start)
    ) {
        Icon(
            imageVector = vectorResource(Res.drawable.ic_account_setting),
            contentDescription = null,
            tint = colors.icon,
            modifier = Modifier
                .background(colors.iconBackground, shape = RoundedCornerShape(8.dp))
                .padding(8.dp)
                .size(24.dp)
        )
        Text(
            text = "${labels.title} ${controller.userName}",
            color = colors.title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )

    }
    val interaction = remember { MutableInteractionSource() }
    val hovered by interaction.collectIsHoveredAsState()
    Icon(
        imageVector = vectorResource(Res.drawable.ic_cancel),
        contentDescription = null,
        tint = colors.cancel,
        modifier = Modifier
            .hoverable(interaction)
            .background(
                color = if (hovered) colors.cancelBackground.focused else colors.cancelBackground.unfocused,
                shape = CircleShape
            )
            .padding(8.dp)
            .size(16.dp)
            .onClick(onDismiss)
    )
}