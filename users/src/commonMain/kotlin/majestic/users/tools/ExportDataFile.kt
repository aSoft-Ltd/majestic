package majestic.users.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.SmartSelect
import majestic.users.tools.export.DataExport
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_arrow_down
import tz.co.asoft.majestic_users.generated.resources.ic_share

@Composable
fun ExportDataFile(
    colors: ColorPair,
    onAction: (DataExport) -> Unit = {},
    modifier: Modifier = Modifier,
) = SmartSelect(
    modifier = modifier,
    items = DataExport.entries.toList(),
    item = {
        Item(color = colors.foreground, action = it)
    },
    selected = { Placeholder(colors) },
    placeholder = { Placeholder(colors) },
    onChange = { it?.let(onAction) },
    drawerContainerColor = colors.background,
    shape = CircleShape,
    dropDownShape = RoundedCornerShape(12.dp)
)

@Composable
private fun Placeholder(colors: ColorPair) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .clip(CircleShape)
            .background(colors.foreground.copy(.1f))
            .padding(vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = 5.dp,
            alignment = Alignment.CenterHorizontally
        ),
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(Res.drawable.ic_share),
            tint = colors.foreground,
            contentDescription = null,
        )
        Text(
            text = "Export",
            fontSize = 14.sp,
            color = colors.foreground,
            lineHeight = 0.1.sp
        )
        Spacer(Modifier.width(2.dp))
        Icon(
            modifier = Modifier.size(10.dp),
            painter = painterResource(Res.drawable.ic_arrow_down),
            tint = colors.foreground.copy(0.5f),
            contentDescription = null,
        )
    }
}

@Composable
private fun Item(color: Color, action: DataExport) = Row(
    modifier = Modifier.fillMaxWidth(),
    horizontalArrangement = Arrangement.spacedBy(10.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        modifier = Modifier.size(16.dp),
        painter = painterResource(action.icon),
        tint = color,
        contentDescription = null,
    )
    Text(text = action.label, fontSize = 14.sp, color = color)
}