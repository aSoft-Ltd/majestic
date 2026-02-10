package majestic.shared.credit.tools

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun RowScope.HeaderCell(
    text: String,
    weight: Float,
    props: CreditTableProps,
    align: Alignment = Alignment.CenterStart
) {
    val textAlign = when (align) {
        Alignment.Center -> TextAlign.Center
        Alignment.CenterEnd, Alignment.End, Alignment.TopEnd, Alignment.BottomEnd -> TextAlign.End
        else -> TextAlign.Start
    }

    Text(
        text = text,
        color = props.colors.surfaceColor.foreground.copy(.5f),
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium,
        textAlign = textAlign,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.weight(weight).fillMaxWidth()
    )
}