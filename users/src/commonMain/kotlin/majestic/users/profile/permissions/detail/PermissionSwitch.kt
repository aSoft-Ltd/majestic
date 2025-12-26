package majestic.users.profile.permissions.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ToggleSwitch


@Composable
internal fun PermissionSwitch(
    modifier: Modifier,
    props: DetailedItemProperties,
    onSwitching: (Boolean) -> Unit
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
    verticalAlignment = Alignment.CenterVertically
) {
    Text(
        text = if (props.itemState) props.item.switch.first else props.item.switch.second,
        fontSize = 16.sp,
        maxLines = 3,
        color = props.colors.description,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Normal
    )
    ToggleSwitch(
        checked = props.itemState,
        onCheckedChange = onSwitching,
        height = 20.dp,
        circleSize = 15.dp,
        width = 40.dp,
        circlePadding = 0.dp,
        shape = RoundedCornerShape(10.dp),
        colors = props.colors.switch
    )
}