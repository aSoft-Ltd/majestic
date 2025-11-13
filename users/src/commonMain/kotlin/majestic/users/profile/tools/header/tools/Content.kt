package majestic.users.profile.tools.header.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor

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
    }
    Row(
        horizontalArrangement = Arrangement.spacedBy(if (orientation is Landscape) 20.dp else 4.dp),
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
}