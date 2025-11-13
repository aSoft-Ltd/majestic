package majestic.users.profile.tools.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.ThemeColor
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

@Composable
internal fun FlowItem(
    title: String,
    description: String,
    theme: ThemeColor,
    resource: DrawableResource,
    resourceSize: Dp,
    orientation: ScreenOrientation,
    titleSize: TextUnit = 16.sp,
    subtitleSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(if (orientation is Landscape) 2.dp else 1.dp)
) {
    Icon(
        modifier = Modifier.size(resourceSize),
        imageVector = vectorResource(resource),
        contentDescription = null,
        tint = theme.surface.contra.color.copy(.3f)
    )
    Text(
        text = title,
        fontWeight = fontWeight,
        fontSize = titleSize,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = theme.surface.contra.color
    )
    Text(
        text = description,
        fontWeight = fontWeight,
        fontSize = subtitleSize,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = theme.surface.contra.color.copy(alpha = 0.5f)
    )
}
