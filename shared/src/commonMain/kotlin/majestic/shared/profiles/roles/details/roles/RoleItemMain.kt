package majestic.shared.profiles.roles.details.roles

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
import majestic.shared.profiles.roles.data.Role

@Composable
internal fun RoleItemMain(
    modifier: Modifier,
    index: Int,
    role: Role,
    colors: RoleItemColors
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(4.dp),
    horizontalAlignment = Alignment.Start
) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = colors.title.unfocused)) {
                append("${index + 1}. ")
            }
            append(role.title)
        },
        color = colors.title.focused,
        fontWeight = FontWeight.SemiBold,
        fontSize = 15.sp
    )
    Text(
        text = role.description,
        color = colors.subtitle,
        fontSize = 13.sp,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis
    )
}