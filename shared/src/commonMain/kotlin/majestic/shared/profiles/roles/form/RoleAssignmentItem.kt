package majestic.shared.profiles.roles.form

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.shared.profiles.roles.data.Role
import majestic.shared.profiles.roles.data.RoleAssignmentLabels
import majestic.tooling.onClick

@Composable
internal fun RoleAssignmentItem(
    index: Int,
    role: Role,
    isSelected: Boolean,
    colors: RoleAssignmentColors,
    labels: RoleAssignmentLabels,
    onToggle: () -> Unit
) = Column(modifier = Modifier.fillMaxWidth()) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "$index.",
                    color = colors.roleTitle,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    text = role.title,
                    color = colors.roleTitle,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                text = role.description,
                color = colors.roleDescription,
                fontSize = 13.sp,
                lineHeight = 18.sp
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = labels.viewPermissions,
                color = colors.viewPermissions,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.onClick { }
            )
        }

        val buttonColors = if (isSelected) colors.setupButton else colors.assignButton
        val buttonText = if (isSelected) labels.setup else labels.assign

        Text(
            text = buttonText,
            color = buttonColors.foreground,
            modifier = Modifier
                .clip(RoundedCornerShape(18.dp))
                .background(buttonColors.background)
                .then(
                    if (buttonColors.border != null) Modifier.border(
                        1.dp,
                        buttonColors.border,
                        RoundedCornerShape(18.dp)
                    ) else Modifier
                )
                .onClick { onToggle() }
                .padding(horizontal = 24.dp, vertical = 8.dp),
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}
