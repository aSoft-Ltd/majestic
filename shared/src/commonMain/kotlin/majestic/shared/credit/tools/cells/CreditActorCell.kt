package majestic.shared.credit.tools.cells

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.shared.credit.tools.CreditTableColors
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun CreditActorCell(
    logo: DrawableResource,
    name: String,
    id: String,
    colors: CreditTableColors,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
) {
    val txtColor = colors.surfaceColor.foreground
    Image(
        painter = painterResource(logo),
        contentDescription = null,
        modifier = Modifier.padding(end = 10.dp).size(42.dp).clip(RoundedCornerShape(8.dp))
    )
    Column(modifier = Modifier.weight(1f)) {
        Text(
            text = name,
            color = txtColor,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = id,
            color = txtColor.copy(alpha = 0.6f),
            fontSize = 12.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}