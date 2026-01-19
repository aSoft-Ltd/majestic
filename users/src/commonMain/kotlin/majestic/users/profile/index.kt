package majestic.users.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.users.profile.header.tools.Body
import majestic.users.profile.header.tools.FlowItemColors
import majestic.users.profile.header.tools.HeadData
import majestic.users.profile.header.tools.toHeadContentColors
import org.jetbrains.compose.resources.painterResource

data class HeadColors(
    val content: Color,
    val background: Color,
    val title: Color,
    val flow: FlowItemColors
)

@Composable
fun Head(
    data: HeadData,
    colors: HeadColors,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(10.dp),
    verticalAlignment = Alignment.CenterVertically
) {
    when (data.avatar) {
        null -> Box(
            modifier

            = Modifier
                .size(if (orientation is Landscape) 140.dp else 70.dp)
                .clip(CircleShape)
                .background(colors.background.copy(.7f)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = data.name.firstOrNull()?.uppercase() ?: "",
                color = colors.content,
                fontSize = if (orientation is Landscape) 40.sp else 20.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1
            )
        }

        else -> Box(modifier = Modifier.size(if (orientation is Landscape) 100.dp else 70.dp)) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(CircleShape),
                painter = painterResource(data.avatar),
                contentDescription = null,
            )
            if (orientation is Portrait) Image(
                modifier = Modifier
                    .padding(start = 15.dp, bottom = 10.dp)
                    .align(Alignment.BottomEnd)
                    .border(1.dp, colors.background, CircleShape)
                    .clip(CircleShape)
                    .size(15.dp),
                contentScale = ContentScale.Crop,
                painter = painterResource(data.flag),
                contentDescription = null,
            )
        }
    }


    Body(
        modifier = Modifier.wrapContentHeight().fillMaxWidth(),
        orientation = orientation,
        data = data,
        colors = colors.toHeadContentColors()
    )
}

