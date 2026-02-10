package majestic.shared.attachment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.icons.Res
import majestic.icons.ic_download_02
import majestic.icons.ic_jpg
import majestic.icons.ic_multiplication_sign
import majestic.icons.ic_printer_01
import majestic.icons.ic_share_02
import majestic.shared.attachment.tools.iconButton
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun PreviewHeader(
    title: String,
    colors: ColorPair,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) = Box(modifier = modifier, contentAlignment = Alignment.Center) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.align(Alignment.CenterStart)
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_jpg),
            contentDescription = null,
            tint = Color(0xFF17A7E2)
        )
        Text(
            text = title,
            color = colors.foreground,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.align(Alignment.Center)
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_share_02),
            contentDescription = null,
            tint = colors.foreground.copy(0.7f),
            modifier = Modifier.iconButton(background = colors.background)
        )
        Icon(
            painter = painterResource(Res.drawable.ic_printer_01),
            contentDescription = null,
            tint = colors.foreground.copy(0.7f),
            modifier = Modifier.iconButton(background = colors.background)
        )
        Icon(
            painter = painterResource(Res.drawable.ic_download_02),
            contentDescription = null,
            tint = colors.foreground.copy(0.7f),
            modifier = Modifier.iconButton(background = colors.background)
        )
    }

    Icon(
        modifier = Modifier.align(Alignment.CenterEnd)
            .iconButton(
                background = colors.background,
                onClick = onDismiss
            ),
        painter = painterResource(Res.drawable.ic_multiplication_sign),
        tint = colors.foreground,
        contentDescription = "Icon"
    )
}
