package majestic.shared.tools.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.button.appearence.closeModalIconButton
import majestic.button.basic.CloseModalButton
import majestic.icons.Res
import majestic.icons.ic_arrow_left
import majestic.icons.ic_tick_02
import majestic.tooling.onClick
import org.jetbrains.compose.resources.painterResource

@Composable
fun DialogHeader(
    icon: ImageVector? = null,
    title: String,
    orientation: ScreenOrientation,
    colors: ModalColors,
    subtitle: String? = null,
    onClose: () -> Unit,
    onBack: () -> Unit,
    onSubmit: () -> Unit,
    showBackNavigation: Boolean = false,
    modifier: Modifier = Modifier,
) = Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
    modifier = modifier
) {
    if (showBackNavigation && orientation is Portrait) Icon(
        modifier = Modifier.size(24.dp).onClick { onBack() },
        painter = painterResource(Res.drawable.ic_arrow_left),
        tint = colors.text,
        contentDescription = "Icon"
    )

    if (icon != null) Icon(
        imageVector = icon,
        contentDescription = title,
        tint = colors.headerIconTint,
        modifier = Modifier
            .size(44.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(colors.headerIconBg)
            .padding(10.dp)
    )

    Column(
        modifier = Modifier.weight(1f).wrapContentHeight(),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = title,
            fontSize = if (orientation is Landscape) 16.sp else 14.sp,
            lineHeight = 2.sp,
            fontWeight = FontWeight.Medium,
            color = colors.text,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (subtitle != null) {
            Spacer(Modifier.height(5.dp))
            Text(
                text = subtitle,
                fontSize = if (orientation is Landscape) 14.sp else 12.sp,
                lineHeight = 2.sp,
                color = colors.text.copy(0.4f),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }

    Row(horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)) {
        if (!showBackNavigation || orientation is Landscape) CloseModalButton(
            modifier = Modifier.closeModalIconButton(
                color = colors.closeButton,
                onClick = onClose
            )
        ) else Icon(
            painter = painterResource(Res.drawable.ic_tick_02),
            tint = colors.text,
            contentDescription = null,
            modifier = Modifier.size(25.dp).onClick { onSubmit() }
        )
    }
}