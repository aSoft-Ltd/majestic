package majestic.popup

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

class Items<T>(
    val data: Collection<T>,
    val item: Item<T>,
    val shape: Shape = RoundedCornerShape(8.dp),
    val modifier: Modifier = Modifier,
)