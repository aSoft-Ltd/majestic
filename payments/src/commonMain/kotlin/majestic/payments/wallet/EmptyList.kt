package majestic.payments.wallet

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.Light
import majestic.ThemeColor
import majestic.button.appearence.plusButton
import majestic.button.basic.PlusButton
import majestic.payments.labels.SectionLabels
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_wallet_add_01

@Composable
fun EmptyList(
    labels: SectionLabels,
    theme: ThemeColor,
    onAdd: () -> Unit,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(12.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Icon(
        modifier = Modifier.size(52.dp),
        painter = painterResource(Res.drawable.ic_wallet_add_01),
        tint = theme.dominant.actual.color,
        contentDescription = null
    )
    Text(
        text = labels.label,
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 1.sp,
        color = theme.surface.contra.color
    )
    Text(
        text = labels.description,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        textAlign = TextAlign.Center,
        color = theme.surface.contra.color.copy(alpha = 0.5f)
    )

    PlusButton(
        modifier = Modifier.plusButton(
            colors = ColorPair(
                foreground = theme.surface.actual.color,
                background = if (this is Light) theme.dominant.actual.color else theme.surface.contra.color
            ),
            onClick = onAdd
        )
    )
}