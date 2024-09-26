@file:OptIn(ExperimentalMaterial3Api::class)

package majestic

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun <T> SmartSelect(
    items: Collection<T>,
    item: @Composable (T) -> Unit = { Text("$it") },
    selected: @Composable (T) -> Unit = item,
    value: T? = null,
    placeholder: @Composable () -> Unit = {
        Text("Select", modifier = Modifier.fillMaxWidth())
    },
    onClick: ((T) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var candidate by remember(value) { mutableStateOf(value) }

    DumbSelect(
        items = items,
        item = item,
        selected = selected,
        value = candidate,
        placeholder = placeholder,
        modifier = modifier,
        onClick = {
            candidate = if(it==candidate) null else it
            onClick?.invoke(it)
        }
    )
}