package majestic.payments.wallet.form

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.Checkbox
import majestic.CheckboxColors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

data class PaymentCardColors(
    val background: Color,
    val foreground: Color,
    val checkbox: CheckboxColors
)

@Composable
internal fun PaymentCard(
    title: String,
    description: String,
    image: DrawableResource,
    colors: PaymentCardColors,
    selected: Boolean = false,
    modifier: Modifier = Modifier,
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    Row(
        modifier = Modifier.weight(1f),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = Modifier.size(40.dp).clip(RoundedCornerShape(8.dp)),
            painter = painterResource(image),
            contentDescription = null
        )
        Column {
            Text(
                text = title,
                fontSize = 14.sp,
                lineHeight = 1.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = colors.foreground
            )
            Text(
                text = description,
                fontSize = 14.sp,
                lineHeight = 1.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = colors.foreground.copy(0.5f)
            )
        }
    }
    val checkboxColors = if (selected) colors.checkbox.selected else colors.checkbox.unselected
    Checkbox(
        selected = selected,
        colors = checkboxColors,
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.size(16.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(checkboxColors.background)
            .border(1.dp, color = checkboxColors.border, RoundedCornerShape(5.dp))
    )
}
