package majestic.users.dashboard.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ActionButton
import majestic.ButtonColors
import majestic.tooling.onClick
import org.jetbrains.compose.resources.vectorResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_add

data class HeaderColors(
    val background: Color,
    val manage: ButtonColors,
    val add: Color,
    val tint: Color,
    val title: Color
)

data class Labels(
    val title: String,
    val manage: String
)

data class HeaderProps(
    val labels: Labels,
    val colors: HeaderColors
)

@Composable
internal fun Header(
    modifier: Modifier,
    props: HeaderProps,
    add: () -> Unit,
    manage: () -> Unit
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    val add = MutableInteractionSource()
    val hovered by add.collectIsHoveredAsState()
    Text(
        text = props.labels.title,
        color = props.colors.title,
        fontSize = 20.sp,
        minLines = 1,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ActionButton(
            modifier = Modifier,
            colors = props.colors.manage,
            text = props.labels.manage,
            onClick = { add() },
            shape = RoundedCornerShape(20.dp)
        )
        Icon(
            modifier = Modifier
                .onClick { manage() }
                .hoverable(interactionSource = add)
                .rotate(if (hovered) 90f else 0f)
                .clip(CircleShape)
                .background(
                    shape = CircleShape,
                    color = if (hovered) props.colors.add.copy(.8f) else props.colors.add.copy(.7f)
                )
                .padding(16.dp)
                .size(24.dp),
            imageVector = vectorResource(Res.drawable.ic_add),
            contentDescription = "Add Icon",
        )
    }
}