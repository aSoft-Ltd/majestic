package majestic.users.profile.roles.campus

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.users.labels.roles.RolesLabels

@Composable
fun CampusInfo(
    campusName: String,
    rolesCount: Int,
    labels: RolesLabels.CampusLabels,
    theme: majestic.ThemeColor
) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(
            text = campusName,
            color = theme.surface.contra.color,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            lineHeight = 25.2.sp,
        )
        Text(
            text = "$rolesCount ${if (rolesCount > 1) labels.rolesPlural else labels.rolesSingular}",
            color = theme.surface.contra.color.copy(alpha = 0.30f),
            fontSize = 14.sp,
            fontWeight = FontWeight(450),
            lineHeight = 19.6.sp,
        )
    }
}