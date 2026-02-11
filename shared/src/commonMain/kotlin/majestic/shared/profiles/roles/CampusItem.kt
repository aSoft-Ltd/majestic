package majestic.shared.profiles.roles

import academia.generated.resources.Res
import academia.generated.resources.ic_add
import academia.generated.resources.ic_dots
import academia.generated.resources.ic_school_solid
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import majestic.tooling.onClick
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.academia.playground.ronald.CampusData

@Composable
internal fun CampusItem(
    modifier: Modifier,
    campus: CampusData,
    theme: ThemeColor,
    orientation: ScreenOrientation,
    onAdd: () -> Unit,
    onMore: () -> Unit
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    val iconColor = Color(0xFF4DB6AC)
    val iconBackgroundColor = iconColor.copy(alpha = 0.1f)
    Row(
        modifier = Modifier.wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start)
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_school_solid),
            contentDescription = null,
            tint = iconColor,
            modifier = Modifier
                .clip(RoundedCornerShape(6.dp))
                .background(iconBackgroundColor)
                .padding(8.dp)
                .size(24.dp)
        )

        Column(
            modifier = Modifier.wrapContentSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = campus.name,
                fontWeight = FontWeight.SemiBold,
                color = theme.surface.contra.color,
                fontSize = 15.sp,
            )
            Text(
                text = "${campus.roles} Roles",
                color = theme.surface.contra.color.copy(alpha = 0.7f),
                fontSize = 13.sp
            )
        }
    }

    Row(
        modifier = Modifier.padding(end = 10.dp).wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val addInteraction = remember { MutableInteractionSource() }
        val isAddHovered by addInteraction.collectIsHoveredAsState()
        if (orientation is Landscape) Icon(
            modifier = Modifier
                .onClick { onAdd() }
                .pointerHoverIcon(PointerIcon.Hand)
                .hoverable(addInteraction)
                .background(if (isAddHovered) theme.surface.actual.color.copy(.5f) else Color.Transparent, shape = CircleShape)
                .padding(8.dp),
            painter = painterResource(Res.drawable.ic_add),
            contentDescription = "Add",
            tint = theme.surface.contra.color
        )

        Icon(
            modifier = Modifier
                .onClick { onMore() }
                .pointerHoverIcon(PointerIcon.Hand)
                .graphicsLayer {
                    rotationZ = if (orientation is Portrait) 90f else 0f
                }
                .padding(8.dp),
            painter = painterResource(Res.drawable.ic_dots),
            contentDescription = "More",
            tint = theme.surface.contra.color
        )
    }
}
