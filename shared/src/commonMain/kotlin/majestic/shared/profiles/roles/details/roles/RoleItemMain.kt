package majestic.shared.profiles.roles.details.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.shared.profiles.roles.data.Role
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun RoleItemMain(
    modifier: Modifier,
    index: Int,
    role: Role,
    colors: RoleItemColors,
    orientation: ScreenOrientation
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        painter = painterResource(role.resource),
        contentDescription = null,
        tint = colors.icon.foreground,
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(colors.icon.background)
            .padding(8.dp)
            .size(24.dp)
    )

    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "${index + 1}. ${role.title}",
            color = colors.title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 15.sp
        )
        Text(
            text = role.description,
            color = colors.subtitle,
            fontSize = 13.sp,
            maxLines = if (orientation is Landscape) 1 else 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}
