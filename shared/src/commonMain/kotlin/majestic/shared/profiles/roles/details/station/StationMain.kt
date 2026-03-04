package majestic.shared.profiles.roles.details.station

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.shared.profiles.roles.data.RoleData
import majestic.shared.profiles.roles.data.RoleLabels
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun StationMain(
    modifier: Modifier,
    station: RoleData,
    colors: StationItemColors,
    labels: RoleLabels
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start)
) {
    Icon(
        painter = painterResource(station.resource),
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
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Text(
            text = station.station,
            fontWeight = FontWeight.SemiBold,
            color = colors.title,
            fontSize = 15.sp,
        )
        Text(
            text = "${station.roles.size} ${labels.roles}",
            color = colors.subtitle,
            fontSize = 13.sp
        )
    }
}
