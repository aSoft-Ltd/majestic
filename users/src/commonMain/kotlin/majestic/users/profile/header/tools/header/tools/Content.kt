package majestic.users.profile.header.tools.header.tools

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.resources.painterResource
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

data class HeadContentColors(
    val whiteBackground: Color,
    val content: Color,
    val title: Color,
    val flow: FlowItemColors
)

@OptIn(ExperimentalTime::class)
@Composable
internal fun Content(
    modifier: Modifier,
    orientation: ScreenOrientation,
    data: HeadData,
    colors: HeadContentColors
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(if (orientation is Landscape) 10.dp else 5.dp)
) {
    val now = Clock.System.now()
    val year = now.toLocalDateTime(TimeZone.currentSystemDefault()).year

    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = data.name,
            color = colors.title,
            fontSize = if (orientation is Landscape) 20.sp else 14.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 1.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        if (orientation is Landscape) Image(
            modifier = Modifier
                .padding(start = 10.dp)
                .size(15.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            painter = painterResource(data.flag),
            contentDescription = null,
        )
        if (orientation is Landscape) Text(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .background(colors.whiteBackground.copy(.3f))
                .padding(horizontal = 5.dp, vertical = 2.dp),
            text = data.gender,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontSize = 10.sp,
            lineHeight = 1.sp,
            color = colors.content,
        )
    }
    when (orientation) {
        is Landscape -> Row(
            modifier = Modifier.padding(start = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(40.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            data.list.forEach { item ->
                FlowItem(
                    title = item.title,
                    description = item.description,
                    colors = colors.flow,
                    titleSize = 12.sp,
                    subtitleSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.wrapContentSize(),
                    resource = item.icon,
                    resourceSize = 18.dp
                )
            }
        }

        is Portrait -> Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .clip(RoundedCornerShape(5.dp))
                    .background(colors.whiteBackground.copy(.05f))
                    .padding(horizontal = 5.dp, vertical = 2.dp),
                text = data.gender,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = 10.sp,
                lineHeight = 1.sp,
                color = colors.content,
            )
            data.list.filter {
                it.title.equals("Date of Birth", true) || it.title.equals(
                    "joined",
                    true
                )
            }.forEach { item ->
                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(2.dp))
                        .background(colors.whiteBackground.copy(.05f))
                        .padding(horizontal = 5.dp, vertical = 2.dp),
                    text = if (item.title.equals("joined", true)) {
                        "${item.title} ${item.description.drop(2)}"
                    } else if (item.title.equals("Date of Birth", true)) {
                        "Age ${(item.description.takeLast(4).toIntOrNull() ?: 0)}"
                    } else "",
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    fontSize = 10.sp,
                    lineHeight = 1.sp,
                    color = colors.content,
                )
            }
        }
    }
}