package majestic.users.profile.roles.roleItem

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ThemeColor
import majestic.users.labels.roles.RoleItemLabels
import majestic.users.labels.roles.RolesLabels
import majestic.users.profile.roles.RoleActionType
import majestic.users.profile.roles.RoleItemColors


@Composable
fun RoleItemContent(
    name: String,
    description: String,
    actionType: RoleActionType,
    labels: RoleItemLabels,
    colors: RoleItemColors,
    theme: ThemeColor,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.widthIn(min = 300.dp), verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                color = theme.surface.contra.color,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                lineHeight = 22.4.sp
            )

            if (actionType == RoleActionType.SETUP) {
                RoleSetupBadge(labels = labels, colors = colors)
            }
        }

        Text(
            text = description,
            color = theme.surface.contra.color.copy(alpha = 0.50f),
            fontSize = 14.sp,
            lineHeight = 19.6.sp
        )
    }
}