package majestic.shared.profiles.roles.details.responsibilities

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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

@Composable
internal fun Description(
    modifier: Modifier,
    orientation: ScreenOrientation,
    colors: ResponsibilityColors,
    index: Int,
    responsibility: Permission,
    type: ResponsibilityType
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(2.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = when (orientation) {
                            is Landscape -> colors.title.focused
                            is Portrait -> colors.title.unfocused
                        },
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    when (type) {
                        is NumberResponsibility -> append("${index + 1}  ")
                        is IconResponsibility -> append("")
                    }
                }
                append(responsibility.title)
            },
            color = colors.title.focused,
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
}