package majestic.shared.profiles.roles.assign.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.button.appearence.cardButton
import majestic.button.basic.CardButton
import majestic.shared.profiles.roles.data.Role
import majestic.shared.profiles.roles.data.RoleAssignmentLabels
import majestic.tooling.onClick

data class ButtonColor(
    val background: Color,
    val foreground: Color,
    val border: Color?
)

data class StatusColors(
    val default: ColorPair,
    val selected: Color
)

data class ItemColors(
    val title: Color,
    val description: Color,
    val view: Color,
    val button: StatusColors,
    val setupButton: ButtonColor
)

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

            if (orientation is Portrait) CardButton(
                text = buttonText,
                fontWeight = FontWeight.Medium,
                modifier = if (isSelected) {
                    Modifier.cardButton(color = colors.button.selected, maxWidth = 175.dp, onClick = onToggle)
                }
                else {
                    Modifier.cardButton(colors = colors.button.default, maxWidth = 175.dp, onClick = onToggle)
                }
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

    if (orientation is Landscape) CardButton(
        text = buttonText,
        fontWeight = FontWeight.Medium,
        modifier = if (isSelected) {
            Modifier.cardButton(color = colors.button.selected, maxWidth = 175.dp, onClick = onToggle)
        }
        else {
            Modifier.cardButton(colors = colors.button.default, maxWidth = 175.dp, onClick = onToggle)
        }
    )
}