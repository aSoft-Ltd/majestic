package majestic.users.profile.permissions

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
import majestic.ColorPair
import majestic.users.tools.data.Permissions
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

data class PermissionData(
    val permission: Permissions,
    val trailIcon: DrawableResource
)

data class PermissionColors(
    val leadIcon: ColorPair,
    val title: Color,
    val description: Color,
    val trailIcon: Color,
    val separator: Color
)

data class PermissionProperties(
    val colors: PermissionColors,
    val item: PermissionData
)

@Composable
fun Permission(modifier: Modifier, props: PermissionProperties) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        imageVector = vectorResource(props.item.permission.resource),
        contentDescription = null,
        tint = props.colors.leadIcon.foreground,
        modifier = Modifier
            .background(color = props.colors.leadIcon.background, shape = RoundedCornerShape(8.dp))
            .padding(10.dp)
            .size(40.dp)
    )
    Column(
        modifier = Modifier.wrapContentHeight().weight(1f),
        verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = props.item.permission.title,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = props.colors.title,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = props.item.permission.description,
            fontSize = 14.sp,
            maxLines = 3,
            color = props.colors.description,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Normal
        )
    }
    Icon(
        imageVector = vectorResource(props.item.trailIcon),
        contentDescription = null,
        tint = props.colors.trailIcon,
        modifier = Modifier.size(40.dp)
    )
}