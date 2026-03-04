package majestic.shared.profiles.roles.assign.form

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.editor.tools.StateColors
import majestic.shared.profiles.roles.data.Role
import majestic.shared.profiles.roles.data.RoleAssignmentLabels
import majestic.tooling.onClick

data class ButtonColor(
    val background: Color,
    val foreground: Color,
    val border: Color?
)

data class StatusColors(
    val foreground: StateColors,
    val background: StateColors
)

data class ItemColors(
    val title: Color,
    val description: Color,
    val view: Color,
    val button: StatusColors,
    val setupButton: ButtonColor
)

@Composable
private fun Modifier.action(
    interaction: MutableInteractionSource,
    hovered: Boolean,
    colors: ItemColors
) = this
    .hoverable(interaction)
    .clip(RoundedCornerShape(18.dp))
    .width(125.dp)
    .background(
        color = when (hovered) {
            true -> colors.button.background.focused
            false -> colors.button.background.unfocused
        }
    )
    .padding(horizontal = 24.dp, vertical = 8.dp)

@Composable
internal fun Item(
    modifier: Modifier,
    index: Int,
    role: Role,
    isSelected: Boolean,
    colors: ItemColors,
    orientation: ScreenOrientation,
    labels: RoleAssignmentLabels,
    onToggle: () -> Unit
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    val buttonText = if (isSelected) labels.unassign else labels.assign
    val interaction = remember { MutableInteractionSource() }
    val hovered by interaction.collectIsHoveredAsState()
    Column(
        modifier = Modifier.fillMaxWidth(if (orientation is Landscape) .8f else 1f),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = if (orientation is Landscape) Arrangement.Start else Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.weight(.9f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "$index.",
                    color = colors.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = role.title,
                    color = colors.title,
                    fontSize = 16.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Bold
                )
            }
            if (orientation is Portrait) Text(
                text = buttonText,
                color = when (isSelected) {
                    true -> colors.button.foreground.focused
                    false -> colors.button.foreground.unfocused
                },
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .hoverable(interaction)
                    .clip(RoundedCornerShape(18.dp))
                    .width(112.dp)
                    .background(
                        color = when (hovered) {
                            true -> colors.button.background.focused
                            false -> colors.button.background.unfocused
                        }
                    )
                    .padding(horizontal = 24.dp, vertical = 8.dp)
                    .onClick { onToggle() },
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
        Text(
            text = role.description,
            color = colors.description,
            fontSize = 13.sp,
            lineHeight = 18.sp
        )
        Text(
            text = labels.view,
            color = colors.view,
            fontSize = 13.sp,
            textDecoration = TextDecoration.Underline,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.onClick { }
        )
    }


    if (orientation is Landscape) Text(
        text = buttonText,
        color = when (isSelected) {
            true -> colors.button.foreground.focused
            false -> colors.button.foreground.unfocused
        },
        textAlign = TextAlign.Center,
        modifier = Modifier
            .action(interaction, hovered, colors)
            .onClick { onToggle() },
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    )
}