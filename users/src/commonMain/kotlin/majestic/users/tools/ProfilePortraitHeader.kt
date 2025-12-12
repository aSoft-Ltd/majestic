package majestic.users.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import captain.Navigator
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.Dark
import majestic.Light
import majestic.NoRippleInteractionSource
import majestic.ThemeColor
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_arrow_left

fun Modifier.contactBackground(
    background: Color,
    theme: ThemeColor,
    orientation: ScreenOrientation
) = background(
    color = when (orientation) {
        is Landscape -> background.copy(alpha = .5f)
        is Portrait -> when (theme) {
            is Dark -> background.copy(alpha = .5f)
            is Light -> theme.dominant.actual.color
        }
    }
)

internal fun ThemeColor.foreground(orientation: ScreenOrientation) = when {
    orientation is Portrait && this is Light -> dominant.contra.color
    else -> surface.contra.color
}

data class ProfilePortraitHeaderColors(
    val background: Color,
    val theme: ThemeColor,
)

@Composable
internal fun ProfilePortraitHeader(
    title: String,
    colors: ProfilePortraitHeaderColors,
    navigator: Navigator,
    content: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
        .fillMaxWidth()
        .contactBackground(colors.background, colors.theme, orientation = Portrait)
        .padding(10.dp)
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        IconButton(
            onClick = { navigator.go(-1) },
            interactionSource = NoRippleInteractionSource
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(Res.drawable.ic_arrow_left),
                tint = colors.theme.foreground(orientation = Portrait),
                contentDescription = "Icon"
            )
        }
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = colors.theme.foreground(orientation = Portrait)
        )
    }
    content()
}
