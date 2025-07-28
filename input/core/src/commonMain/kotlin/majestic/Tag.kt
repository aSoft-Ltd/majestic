package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

fun Modifier.tag(
    color: Color = Color(0x335C6BC0),
    shape: Shape = RoundedCornerShape(5.dp)
) = this
    .clip(shape)
    .background(color)

@Composable
fun Tag(
    label: String,
    fontSize: TextUnit = 12.sp,
    color: Color = Color(0xFF66BB6A),
    modifier: Modifier = Modifier.tag()
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 5.dp),
            text = label,
            color = color,
            overflow = TextOverflow.Ellipsis,
            minLines = 1,
            maxLines = 1,
            fontSize = fontSize,
            lineHeight = 1.sp
        )
    }
}