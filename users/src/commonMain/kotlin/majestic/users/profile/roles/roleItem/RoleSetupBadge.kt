package majestic.users.profile.roles.roleItem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.users.labels.roles.RolesLabels
import majestic.users.profile.roles.RoleItemColors

@Composable
fun RoleSetupBadge(
    labels: RolesLabels.RoleItemLabels, colors: RoleItemColors
) {
    Text(
        modifier = Modifier.clip(CircleShape).background(colors.setupBadgeBackground)
            .padding(horizontal = 8.dp, vertical = 2.dp),
        text = labels.setupBadge,
        fontSize = 12.sp,
        lineHeight = 16.8.sp,
        color = colors.setupBadgeText,
    )
}