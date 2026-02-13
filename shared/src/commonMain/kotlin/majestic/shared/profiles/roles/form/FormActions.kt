package majestic.shared.profiles.roles.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import majestic.shared.profiles.roles.data.RoleAssignmentLabels
import majestic.tooling.onClick

@Composable
internal fun FormActions(
    onCancel: () -> Unit,
    onConfirm: () -> Unit,
    colors: RoleAssignmentColors,
    labels: RoleAssignmentLabels
) = Row(
    modifier = Modifier.fillMaxWidth().padding(24.dp),
    horizontalArrangement = Arrangement.End,
    verticalAlignment = Alignment.CenterVertically
) {
    Text(
        text = labels.cancel,
        color = colors.cancelButton.foreground,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(colors.cancelButton.background)
            .onClick { onCancel() }
            .padding(horizontal = 24.dp, vertical = 10.dp),
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold
    )
    Spacer(Modifier.width(16.dp))
    Text(
        text = labels.assignSelected,
        color = colors.confirmButton.foreground,
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(colors.confirmButton.background)
            .onClick { onConfirm() }
            .padding(horizontal = 24.dp, vertical = 10.dp),
        fontSize = 15.sp,
        fontWeight = FontWeight.SemiBold
    )
}
