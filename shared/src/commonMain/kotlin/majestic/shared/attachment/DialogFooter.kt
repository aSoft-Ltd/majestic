package majestic.shared.attachment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.ColorPair
import majestic.icons.Res
import majestic.icons.ic_add_01
import majestic.icons.ic_minus_01
import majestic.icons.ic_zoom_01
import majestic.shared.attachment.tools.iconButton
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun DialogFooter(
    colors: ColorPair,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(
        space = 10.dp,
        alignment = Alignment.CenterHorizontally
    ),
) {
    Icon(
        painter = painterResource(Res.drawable.ic_minus_01),
        contentDescription = null,
        tint = colors.foreground.copy(0.7f),
        modifier = Modifier.padding(3.dp)
            .iconButton(background = colors.foreground.copy(0.05f))
    )
    Icon(
        painter = painterResource(Res.drawable.ic_zoom_01),
        contentDescription = null,
        tint = colors.foreground,
        modifier = Modifier.size(25.dp)
    )
    Icon(
        painter = painterResource(Res.drawable.ic_add_01),
        contentDescription = null,
        tint = colors.foreground.copy(0.7f),
        modifier = Modifier.padding(5.dp)
            .iconButton(background = colors.foreground.copy(0.05f))
    )
}
