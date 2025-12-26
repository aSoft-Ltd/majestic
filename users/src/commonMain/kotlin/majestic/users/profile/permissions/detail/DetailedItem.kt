package majestic.users.profile.permissions.detail


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.ToggleSwitchColors
import majestic.users.tools.data.Permission
import org.jetbrains.compose.resources.vectorResource

data class DetailedItemColors(
    val leadIcon: ColorPair,
    val title: Color,
    val description: Color,
    val switch: ToggleSwitchColors,
    val separator: Color
)

data class DetailedItemProperties(
    val colors: DetailedItemColors,
    val itemState: Boolean,
    val item: Permission
)

@Composable
fun DetailedItem(
    modifier: Modifier,
    props: DetailedItemProperties,
    orientation: ScreenOrientation,
    onSwitching: (Boolean) -> Unit
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.End,
    verticalArrangement = if (orientation is Landscape) Arrangement.Top else Arrangement.Center
) {
    Row(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.Top
    ) {

        Icon(
            imageVector = vectorResource(props.item.resource),
            contentDescription = null,
            tint = props.colors.leadIcon.foreground,
            modifier = Modifier.size(24.dp)
        )
        Column(
            modifier = Modifier.wrapContentHeight().weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.Top),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = props.item.title,
                fontSize = 16.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = props.colors.title,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = props.item.description,
                fontSize = 14.sp,
                maxLines = 3,
                color = props.colors.description,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Normal
            )
        }
        if (orientation is Landscape) PermissionSwitch(
            modifier = Modifier.wrapContentSize(),
            props = props,
            onSwitching = onSwitching
        )
    }
    if (orientation is Portrait) PermissionSwitch(
        modifier = Modifier.wrapContentSize(),
        props = props,
        onSwitching = onSwitching
    )
}