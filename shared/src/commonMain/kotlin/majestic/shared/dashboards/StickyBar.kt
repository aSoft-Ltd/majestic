package majestic.shared.dashboards

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import majestic.shared.tools.StickyBarColors

@Composable
fun StickyBar(
    colors: StickyBarColors,
    title: String,
    detail: String,
    modifier: Modifier = Modifier,
) = Column(modifier = modifier) {
    Text(
        text = title,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = colors.title,
    )
    Text(
        text = detail,
        fontSize = 16.sp,
        color = colors.subtitle,
    )
}