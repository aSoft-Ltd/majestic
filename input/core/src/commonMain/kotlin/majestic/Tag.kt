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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Tag(
    label: String,
    fontSize: TextUnit = 12.sp,
    color: Color = Color(0xFF64B5F6),
    backgroundColor: Color = Color(0xFF5C6BC0).copy(alpha = 0.2f)
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(backgroundColor),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(vertical = 2.dp, horizontal = 5.dp),
            text = label,
            color = color,
            fontSize = fontSize,
            lineHeight = 1.sp
        )
    }
}