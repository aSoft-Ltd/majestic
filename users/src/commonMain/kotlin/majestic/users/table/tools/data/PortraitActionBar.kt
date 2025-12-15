package majestic.users.table.tools.data


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ThemeColor
import majestic.tooling.onClick
import org.jetbrains.compose.resources.vectorResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_back
import tz.co.asoft.majestic_users.generated.resources.ic_search
import tz.co.asoft.majestic_users.generated.resources.ic_vertical_settings

@Composable
internal fun PortraitActionBar(
    modifier: Modifier,
    label: String,
    theme: ThemeColor,
    onBack: () -> Unit,
    autoHide: @Composable () -> Unit
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    autoHide()
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = vectorResource(Res.drawable.ic_back),
            contentDescription = null,
            tint = theme.surface.contra.color,
            modifier = Modifier.onClick(onBack)
        )
        Text(
            text = label,
            color = theme.surface.contra.color,
            fontSize = 16.sp
        )
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = vectorResource(Res.drawable.ic_vertical_settings),
            contentDescription = null,
            tint = theme.surface.contra.color,
            modifier = Modifier.onClick(onBack)
        )
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = vectorResource(Res.drawable.ic_search),
            contentDescription = null,
            tint = theme.surface.contra.color,
        )
    }

}