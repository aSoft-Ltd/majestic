package majestic

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import majestic.colors.ColorPair

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
    val disabled: SelectMicroColors = SelectMicroColors(
        border = Color.Black.copy(alpha = 0.1f),
        placeholder = Color.Black.copy(alpha = 0.2f),
        text = Color.Black.copy(alpha = 0.4f)
    ),
    val dropdown: ColorPair = ColorPair(
        background = Color.White,
        foreground = Color.Black
    ),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> Select(
    options: List<T>,
    toString: (T) -> String,
    hint: String = "Select",
    value: T? = null,
    colors: SelectColors = SelectColors(),
    icon: ImageVector = Icons.Filled.ArrowDropDown,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(8.dp),
    onSelect: (T?) -> Unit,
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        modifier = modifier,
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                .fillMaxWidth(),
            shape = shape,
            readOnly = true,
            value = value?.let(toString) ?: "",
            onValueChange = {},
            placeholder = {
                Text(
                    text = hint,
                    color = colors.blurred.placeholder
                )
            },
            trailingIcon = {
                val animateRotation by animateFloatAsState(
                    targetValue = if (expanded) -180f else 0f,
                    animationSpec = tween(durationMillis = 300)
                )
                Icon(
                    modifier = Modifier.graphicsLayer { rotationX = animateRotation },
                    imageVector = icon,
                    contentDescription = "Dropdown Arrow",
                    tint = if (expanded) colors.focused.text else colors.blurred.text
                )
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                focusedBorderColor = colors.focused.border,
                unfocusedBorderColor = colors.blurred.border,
                disabledBorderColor = colors.disabled.border,
                focusedTextColor = colors.focused.text,
                unfocusedTextColor = colors.blurred.text,
                disabledTextColor = colors.disabled.text,
            ),
        )
        ExposedDropdownMenu(
            containerColor = colors.dropdown.background,
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option: T ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = toString(option),
                            color = colors.dropdown.foreground
                        )
                    },
                    onClick = {
                        expanded = false
                        if (option == value) onSelect(null) else onSelect(option)
                    }
                )
            }
        }
    }
}
