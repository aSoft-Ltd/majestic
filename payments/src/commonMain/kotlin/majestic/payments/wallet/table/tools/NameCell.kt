package majestic.payments.wallet.table.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.payments.tools.table.TableColors
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_payments.generated.resources.Res
import tz.co.asoft.majestic_payments.generated.resources.ic_wallet_02_solid

@Composable
internal fun NameCell(
    name: String,
    colors: TableColors,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(12.dp)
) {
    Icon(
        modifier = Modifier.size(46.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(colors.icon.copy(0.1f))
            .padding(12.dp),
        painter = painterResource(Res.drawable.ic_wallet_02_solid),
        tint = colors.icon,
        contentDescription = null,
    )
    Text(
        text = name,
        color = colors.foreground,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontSize = 14.sp,
        lineHeight = 1.sp
    )
}
