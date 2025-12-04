package majestic.users.profile.tools.header.tools

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
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import majestic.ThemeColor
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalTime::class)
@Composable
internal fun Content(
    modifier: Modifier,
    orientation: ScreenOrientation,
    data: HeadData,
    contentColor: Color,
    theme: ThemeColor
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
            color = contentColor,
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
                .background(theme.surface.contra.color.copy(.3f))
                .padding(horizontal = 5.dp, vertical = 2.dp),
            text = data.gender,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            fontSize = 10.sp,
            lineHeight = 1.sp,
            color = theme.dominant.contra.color,
        )
    }
    when (orientation) {
        is Landscape -> Row(
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            data.list.forEach { item ->
                FlowItem(
                    title = item.title,
                    description = item.description,
                    theme = theme,
                    titleSize = 14.sp,
                    subtitleSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.wrapContentSize(),
                    orientation = orientation,
                    resource = item.icon,
                    resourceSize = 20.dp
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
                    .background(theme.surface.contra.color.copy(.3f))
                    .padding(horizontal = 5.dp, vertical = 2.dp),
                text = data.gender,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = 10.sp,
                lineHeight = 1.sp,
                color = theme.dominant.contra.color,
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
                        .background(theme.surface.contra.color.copy(.3f))
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
                    color = theme.dominant.contra.color,
                )
            }
        }
    }
}