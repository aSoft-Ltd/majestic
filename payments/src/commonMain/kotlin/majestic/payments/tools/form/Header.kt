package majestic.payments.tools.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.payments.labels.SectionLabels
import majestic.tooling.onClick
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_add

@Composable
internal fun Header(
    colors: FormHeaderColors,
    labels: SectionLabels,
    icon: DrawableResource,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    Row(
        modifier = Modifier.weight(1f),
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
                color = colors.foreground.copy(0.5f),
                fontSize = 12.sp,
                lineHeight = 1.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
    Icon(
        modifier = Modifier.size(35.dp)
            .onClick(onClose)
            .padding(5.dp)
            .graphicsLayer { rotationZ = 45f },
        painter = painterResource(Res.drawable.ic_add),
        tint = colors.close.foreground,
        contentDescription = "Close Icon"
    )
}
