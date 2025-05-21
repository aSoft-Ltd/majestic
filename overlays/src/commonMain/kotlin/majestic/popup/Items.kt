package majestic.popup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

class Items<T>(
    val data: Collection<T>,
    val item: Item<T>,
    val shape: Shape = RoundedCornerShape(8.dp),
    val modifier: Modifier = Modifier,
) {
    constructor(
        data: Collection<T>,
        shape: Shape = RoundedCornerShape(8.dp),
        modifier: Modifier = Modifier,
        item: @Composable (T) -> Unit,
    ) : this(
        data = data,
        item = Item(
            modifier = Modifier.fillMaxWidth(),
            content = item,
        ),
        shape = shape,
        modifier = modifier
    )
}