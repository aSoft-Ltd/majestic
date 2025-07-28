package majestic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp

@Composable
fun <T> SmartSelect(
    items: Collection<T>,
    item: @Composable (T) -> Unit = { Text("$it") },
    selected: @Composable (T) -> Unit = item,
    value: T? = null,
    placeholder: @Composable () -> Unit = {
        Text("Select", modifier = Modifier.fillMaxWidth())
    },
    onExpanded: ((Boolean) -> Unit)? = null,
    onClick: ((T) -> Unit)? = null,
    onChange: ((T?) -> Unit)? = null,
    modifier: Modifier = Modifier,
    drawerContainerColor: Color = Color.Unspecified,
    shape: Shape = MenuDefaults.shape,
    dropDownShape: Shape = MenuDefaults.shape,
    dropdownModifier: Modifier = Modifier,
    shadowElevation: Dp = MenuDefaults.ShadowElevation,
    border: BorderStroke? = null,
    tonalElevation: Dp = MenuDefaults.TonalElevation
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
            candidate = if (it == candidate) null else it
            onChange?.invoke(candidate)
            onClick?.invoke(it)
        },
        onExpanded = onExpanded,
        dropDownShape = dropDownShape,
        dropDownContainerColor = drawerContainerColor,
        dropdownModifier = dropdownModifier,
        containerShape = shape,
        shadowElevation = shadowElevation,
        border = border,
        tonalElevation = tonalElevation
    )
}
