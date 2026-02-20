package majestic.shared.profiles.roles

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.icons.Res
import majestic.icons.ic_back
import majestic.tooling.onClick
import org.jetbrains.compose.resources.painterResource

internal fun Modifier.roleScreenHeader(
    colors: RoleHeaderColors,
    orientation: ScreenOrientation
) = this
    .fillMaxWidth()
    .clip(RoundedCornerShape(10.dp))
    .background(colors.background)
    .padding(if (orientation is Landscape) 20.dp else 10.dp)

@Composable
internal fun RolesHeader(
    modifier: Modifier,
    title: String,
    subtitle: String,
    colors: RoleHeaderColors,
    onBack: () -> Unit
) = Row(
    modifier = modifier.horizontalScroll(rememberScrollState()),
    horizontalArrangement = Arrangement.spacedBy(12.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .wrapContentSize()
            .pointerHoverIcon(PointerIcon.Hand)
            .onClick { onBack() }
            .padding(6.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_back),
            contentDescription = null,
            tint = colors.back,
            modifier = Modifier.size(16.dp)
        )
        Text(
            text = "Previous Screen",
            color = colors.back,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
    }

    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = title,
            color = colors.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = subtitle,
            color = colors.subtitle,
            fontSize = 13.sp
        )
    }
}
