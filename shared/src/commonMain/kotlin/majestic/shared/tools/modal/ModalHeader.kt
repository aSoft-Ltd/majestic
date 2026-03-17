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
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.button.appearence.closeModalIconButton
import majestic.button.basic.CloseModalButton

@Composable
fun ModalHeader(
    icon: ImageVector? = null,
    iconPainter: Painter? = null,
    title: String,
    orientation: ScreenOrientation,
    colors: ModalColors,
    subtitle: String? = null,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        modifier = modifier
    ) {
        when {
            icon != null -> Icon(
                imageVector = icon,
                contentDescription = title,
                tint = colors.headerIconTint,
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(colors.headerIconBg)
                    .padding(10.dp)
            )

            iconPainter != null -> Icon(
                painter = iconPainter,
                contentDescription = title,
                tint = colors.headerIconTint,
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(6.dp))
                    .background(colors.headerIconBg)
                    .padding(10.dp)
            )
        }

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
                Spacer(Modifier.height(0.5.dp))
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

        CloseModalButton(
            modifier = Modifier
                .closeModalIconButton(
                    color = colors.closeButton,
                    onClick = onClose
                )
        )
    }
}