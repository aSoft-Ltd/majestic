package majestic;

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomLeading(content: @Composable () -> Unit) {
    Row(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .height(38.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        content()
        VerticalDivider(
            modifier = Modifier.padding(start = 10.dp),
            color = Color.Black.copy(alpha = 0.2f),
            thickness = 1.dp
        )
    }
}