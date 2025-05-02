@file:OptIn(ExperimentalMaterial3Api::class)

package majestic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun <T> DumbSelect(
    items: Collection<T>,
    item: @Composable (T) -> Unit = { Text("$it") },
    selected: @Composable (T) -> Unit = item,
    value: T? = null,
    placeholder: @Composable () -> Unit = {
        Text("Select", modifier = Modifier.fillMaxWidth())
    },
    dropdownModifier: Modifier = Modifier,
    onClick: ((T) -> Unit)? = null,
    onExpanded: ((Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier,
    containerShape: Shape = MenuDefaults.shape,
    dropDownContainerColor: Color = Color.Unspecified,
    dropDownShape: Shape = MenuDefaults.shape,
    shadowElevation: Dp = MenuDefaults.ShadowElevation,
    border: BorderStroke? = null,
    tonalElevation: Dp = MenuDefaults.TonalElevation
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = {
            expanded = false
            onExpanded?.invoke(false)
        },
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .clip(shape = containerShape)
                .border(border ?: BorderStroke(0.dp, Color.Transparent))
                .background(color = Color.Transparent, shape = containerShape)
                .exposedDropdownSize()
                .menuAnchor(type = MenuAnchorType.PrimaryEditable)
                .clickable(
                    interactionSource = NoRippleInteractionSource,
                    indication = null,
                    onClick = {
                        expanded = true
                        onExpanded?.invoke(true)
                    }
                )
        ) {
            when (value) {
                null -> placeholder()
                else -> selected(value)
            }
        }
        ExposedDropdownMenu(
            containerColor = dropDownContainerColor,
            shape = dropDownShape,
            shadowElevation = shadowElevation,
            border = BorderStroke(0.dp, Color.Transparent),
            tonalElevation = tonalElevation,
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                onExpanded?.invoke(false)
            },
            modifier = dropdownModifier.exposedDropdownSize()
        ) {
            for (it in items) DropdownMenuItem(
                modifier = Modifier.fillMaxWidth(),
                text = { item(it) },
                onClick = {
                    expanded = false
                    onExpanded?.invoke(expanded)
                    onClick?.invoke(it)
                },
            )
        }
    }
}