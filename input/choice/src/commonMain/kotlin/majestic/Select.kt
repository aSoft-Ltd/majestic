package majestic

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cinematic.watchAsState
import kollections.toKList
import symphony.SingleChoiceField

@Composable
fun <T> Select(
    field: SingleChoiceField<T>,
    item: @Composable (T) -> Unit = { Text("$it") },
    selected: @Composable (T) -> Unit = item,
    placeholder: @Composable () -> Unit = {
        Text("Select", modifier = Modifier.fillMaxWidth())
    },
    onClick: ((T) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val state = field.state.watchAsState()
    DumbSelect(
        items = field.items.toKList(),
        item = item,
        selected = selected,
        value = state.selectedItem,
        placeholder = placeholder,
        onClick = onClick,
        modifier = modifier
    )
}