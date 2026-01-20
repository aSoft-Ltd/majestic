package majestic.users.profile.header.tools

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource

data class FlowItemColors(
    val icon: Color,
    val title: Color,
    val summary: Color
)

@Composable
internal fun FlowItem(
    title: String,
    description: String,
    colors: FlowItemColors,
    resource: DrawableResource,
    resourceSize: Dp,
    titleSize: TextUnit = 16.sp,
    subtitleSize: TextUnit = 14.sp,
    fontWeight: FontWeight = FontWeight.Bold,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(2.dp)
) {
    Column(
        modifier = Modifier.wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(1.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(resourceSize),
            imageVector = vectorResource(resource),
            contentDescription = null,
            tint = colors.icon
        )

        Text(
            text = description,
            fontWeight = fontWeight,
            fontSize = subtitleSize,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = colors.title
        )
    }
    Text(
        text = title,
        fontWeight = fontWeight,
        fontSize = titleSize,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        color = colors.summary
    )
}
