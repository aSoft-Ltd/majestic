package majestic.shared.profiles.roles.details.permissions

import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.shared.profiles.Permission


internal fun Modifier.responsibilityItem(
    interaction: MutableInteractionSource,
    hovered: Boolean,
    orientation: ScreenOrientation,
    colors: ResponsibilityColors,
    index: Int = 0,
    responsibilities: List<Permission>
) = this
    .fillMaxWidth()
    .hoverable(interaction)
    .background(
        color = if (hovered) colors.background.focused else colors.background.unfocused,
        shape = RoundedCornerShape(
            bottomStart = if (index == responsibilities.lastIndex) 10.dp else 0.dp,
            bottomEnd = if (index == responsibilities.lastIndex) 10.dp else 0.dp
        )
    )
    .padding(if (orientation is Landscape) 20.dp else 10.dp)


@Composable
internal fun ResponsibilityItem(
    modifier: Modifier,
    index: Int,
    orientation: ScreenOrientation,
    responsibility: Permission,
    colors: ResponsibilityColors
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(2.dp),
    horizontalAlignment = Alignment.Start
) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = when (orientation) {
                        is Landscape -> colors.title
                        is Portrait -> colors.title.copy(.7f)
                    },
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("${index + 1}  ")
            }
            append(responsibility.title)
        },
        color = colors.title,
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold
    )
    Text(
        text = responsibility.description,
        color = colors.subtitle,
        fontSize = 12.sp,
        maxLines = if (orientation is Landscape) 3 else 5,
        overflow = TextOverflow.Ellipsis
    )
}