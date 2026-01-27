package majestic.payments.tools.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.payments.labels.SectionLabels
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun Header(
    colors: FormHeaderColors,
    labels: SectionLabels,
    icon: DrawableResource,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(10.dp)
) {
    Icon(
        modifier = Modifier.clip(RoundedCornerShape(6.dp))
            .background(colors.icon.background)
            .padding(10.dp),
        painter = painterResource(icon),
        contentDescription = null,
        tint = colors.icon.foreground
    )
    Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
        Text(
            text = labels.label,
            color = colors.foreground,
            lineHeight = 1.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = labels.description,
            color = colors.foreground.copy(0.3f),
            fontSize = 12.sp,
            lineHeight = 1.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
