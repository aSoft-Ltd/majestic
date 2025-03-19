package majestic

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import cinematic.watchAsState
import kollections.toKList
import majestic.colors.ColorPair
import symphony.SingleChoiceField

class SelectMicroColors(
    val border: Color,
    val placeholder: Color,
    val text: Color
)

class SelectColors(
    val focused: SelectMicroColors = SelectMicroColors(
        border = Color(0xFF0061FF),
        placeholder = Color.Black.copy(alpha = 0.4f),
        text = Color.Black
    ),
    val blurred: SelectMicroColors = SelectMicroColors(
        border = Color.Black.copy(alpha = 0.2f),
        placeholder = Color.Black.copy(alpha = 0.4f),
        text = Color.Black
    ),
    val dropdown: ColorPair = ColorPair(
        background = Color.White,
        foreground = Color.Black
    ),
)

@Composable
fun <T> Select(
    field: SingleChoiceField<T>,
    item: @Composable (T) -> Unit = { Text("$it") },
    selected: @Composable (T) -> Unit = item,
    placeholder: @Composable () -> Unit = {
        Text("Select", modifier = Modifier.fillMaxWidth())
    },
    onClick: ((T) -> Unit)? = null,
    onSelected: ((T) -> Unit)? = null,
    onUnSelected: ((T) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val state = field.state.watchAsState()
    DumbSelect(
        items = field.items.toKList(),
        item = item,
        selected = selected,
        value = state.selectedItem,
        placeholder = placeholder,
        onClick = {
            if (it == state.selectedItem) {
                field.unselect()
                onUnSelected?.invoke(it)
            } else {
                field.select(it)
                onSelected?.invoke(it)
            }
            onClick?.invoke(it)
        },
        modifier = modifier
    )
}

@Composable
fun <T> Select(
    items: List<T>,
    hint: String = "Select",
    value: T? = null,
    border: BorderStroke? = null,
    colors: SelectColors = SelectColors(),
    icon: ImageVector = Icons.Filled.ArrowDropDown,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    dropDownShape: Shape = RoundedCornerShape(8.dp),
    onSelect: ((T) -> Unit)? = null,
    option: @Composable (T) -> Unit = { Text("$it") }
) {
    var expanded by remember { mutableStateOf(false) }
    var candidate by remember(value) { mutableStateOf(value) }

    DumbSelect(
        items = items,
        value = candidate,
        modifier = modifier,
        border = border,
        shape = shape,
        dropDownShape = dropDownShape,
        containerColor = colors.focused.placeholder,
        dropDownContainerColor = colors.dropdown.background,
        placeholder = { ItemSelect(icon, colors, expanded) { Text(hint, color = colors.blurred.text) } },
        selected = { ItemSelect(icon, colors, expanded) { option(it) } },
        item = option,
        onExpanded = { expanded = it },
        onClick = {
            candidate = if (it == candidate) null else it
            onSelect?.invoke(it)
        }
    )
}

@Composable
private fun ItemSelect(
    icon: ImageVector,
    colors: SelectColors,
    isExpanded: Boolean = false,
    content: @Composable () -> Unit
) {
    val borderColor = if (isExpanded) colors.focused.border else colors.blurred.border
    val animateRotation by animateFloatAsState(
        targetValue = if (isExpanded) -180f else 0f,
        animationSpec = tween(durationMillis = 300)
    )
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp)
            .border(1.dp, borderColor, RoundedCornerShape(8.dp))
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(11f)) { content() }
        Spacer(Modifier.width(8.dp))
        Icon(
            modifier = Modifier.weight(1f).graphicsLayer { rotationX = animateRotation },
            imageVector = icon,
            contentDescription = "Dropdown Arrow",
            tint = if (isExpanded) colors.focused.text else colors.blurred.text
        )
    }
}
