package majestic.editor.body.chunkUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.editor.insert.Insert

data class BorderColors(
    val focused: Color,
    val unfocused: Color
)

data class TextColors(
    val focused: Color,
    val unfocused: Color
)

data class TextField(
    val text: TextColors,
    val border: BorderColors
)

data class DropDownColors(
    val background: Color = Color.White,
    val field: TextField = TextField(
        text = TextColors(
            focused = Color.Black,
            unfocused = Color.Black.copy(.5f)
        ),
        border = BorderColors(
            focused = Color.Black,
            unfocused = Color.Transparent
        )
    )
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IconedSelector(
    modifier: Modifier,
    options: List<Insert>,
    selectedItem: Insert,
    onItemChange: (Insert) -> Unit,
    colors: DropDownColors,
    shape: Shape = RoundedCornerShape(8.dp),
    customItemContent: (@Composable (Insert) -> Unit),
    leadingIcon: @Composable () -> Unit = {},
    trailingIcon: @Composable () -> Unit = {},
    textStyle: TextStyle = TextStyle(
        fontSize = 12.sp
    )
) {
    var expanded by remember { mutableStateOf(false) }
    val fieldColors = colors.field

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = modifier
    ) {
        OutlinedTextField(
            shape = shape,
            textStyle = textStyle.copy(
                fontSize = 12.sp
            ),
            readOnly = true,
            value = selectedItem.name,
            onValueChange = {},
            trailingIcon = trailingIcon,
            leadingIcon = {

                options.forEach { item ->
                    if (item.name == selectedItem.name) {
                        item.content.invoke()
                    }

                }
            },
            colors = ExposedDropdownMenuDefaults.outlinedTextFieldColors(
                focusedContainerColor = colors.background,
                unfocusedContainerColor = colors.background,
                disabledContainerColor = colors.background,
                focusedBorderColor = fieldColors.border.focused.copy(alpha = 0.2f),
                unfocusedBorderColor = fieldColors.border.unfocused.copy(alpha = 0.2f),
                disabledBorderColor = fieldColors.border.unfocused.copy(alpha = 0.2f),
                focusedTextColor = fieldColors.text.focused.copy(.8f),
                unfocusedTextColor = fieldColors.text.unfocused.copy(.8f),
                disabledTextColor = fieldColors.text.unfocused.copy(.8f),
            ),
            modifier = modifier.menuAnchor(MenuAnchorType.PrimaryNotEditable).padding(horizontal = 16.dp)
        )
        ExposedDropdownMenu(
            containerColor = colors.background,
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { item ->
                DropdownMenuItem(
                    modifier = Modifier.background(color = colors.background),
                    text = {
                        customItemContent(item)
                    },
                    onClick = {
                        expanded = false
                        onItemChange(item)
                    }
                )
            }
        }
    }
}