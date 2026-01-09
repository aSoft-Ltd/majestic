package majestic.users.dashboard.roles

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
    val separator: Color? = null,
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
    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()
    val rotation by animateFloatAsState(
        targetValue = if (hovered) 90f else 0f,
        label = "AddIconRotation"
    )

    Text(
        text = props.labels.title,
        color = props.colors.title,
        fontSize = 16.sp,
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
            modifier = Modifier.height(45.dp),
            colors = props.colors.manage,
            text = props.labels.manage,
            onClick = { manage() },
            shape = RoundedCornerShape(20.dp)
        )
        Icon(
            modifier = Modifier
                .hoverable(interactionSource)
                .onClick { add() }
                .background(
                    color = if (hovered) props.colors.add.copy(.8f) else props.colors.add.copy(.7f),
                    shape = CircleShape
                )
                .size(44.dp)
                .padding(10.dp)
                .rotate(rotation),
            imageVector = vectorResource(Res.drawable.ic_add),
            contentDescription = "Add Icon",
            tint = props.colors.tint
        )
    }
}