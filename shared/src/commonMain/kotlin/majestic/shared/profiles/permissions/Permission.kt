package majestic.shared.profiles.permissions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.shared.profiles.Permissions
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

data class PermissionData(
    val permission: Permissions,
    val trailIcon: DrawableResource
)

data class PermissionColors(
    val background: Color,
    val leadIcon: ColorPair,
    val title: Color,
    val description: Color,
    val trailIcon: Color,
    val separator: Color
)

@Composable
internal fun Permission(
    modifier: Modifier,
    item: PermissionData,
    orientation: ScreenOrientation,
    colors: PermissionColors
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        imageVector = vectorResource(item.permission.resource),
        contentDescription = null,
        tint = colors.leadIcon.foreground.copy(.7f),
        modifier = Modifier
            .background(color = colors.leadIcon.background, shape = RoundedCornerShape(8.dp))
            .padding(if (orientation is Landscape) 16.dp else 8.dp)
            .size(24.dp)
    )
    Column(
        modifier = Modifier.wrapContentHeight().weight(1f),
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = item.permission.title,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = colors.title,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = item.permission.description,
            fontSize = 14.sp,
            maxLines = 3,
            color = colors.description,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Normal
        )
    }

    Icon(
        imageVector = vectorResource(item.trailIcon),
        contentDescription = null,
        tint = colors.trailIcon,
        modifier = Modifier.size(16.dp)
    )
}