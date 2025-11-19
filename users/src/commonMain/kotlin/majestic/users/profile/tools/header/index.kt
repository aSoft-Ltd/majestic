package majestic.users.profile.tools.header

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.Light
import majestic.ThemeColor
import majestic.users.profile.tools.header.tools.Content
import majestic.users.profile.tools.header.tools.HeadData
import org.jetbrains.compose.resources.painterResource


@Composable
fun Head(
    data: HeadData,
    theme: ThemeColor,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(10.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    val contentColor = if (orientation is Portrait && theme.mode is Light) theme.dominant.contra.color
    else theme.surface.contra.color

    if (data.avatar != null) Image(
        modifier = Modifier
            .size(if (orientation is Landscape) 140.dp else 70.dp)
            .clip(RoundedCornerShape(if (orientation is Landscape) 10.dp else 5.dp)),
        painter = painterResource(data.avatar),
        contentDescription = null,
    )
    else Box(
        modifier = Modifier
            .size(if (orientation is Landscape) 140.dp else 70.dp)
            .clip(CircleShape)
            .background(theme.surface.contra.color.copy(.7f)),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = data.name.firstOrNull()?.uppercase() ?: "",
            color = contentColor,
            fontSize = if (orientation is Landscape) 40.sp else 20.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    }

    Content(
        modifier = Modifier.wrapContentHeight().fillMaxWidth(),
        orientation = orientation,
        data = data,
        contentColor,
        theme
    )
}

