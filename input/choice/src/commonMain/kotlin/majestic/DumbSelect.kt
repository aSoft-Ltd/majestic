@file:OptIn(ExperimentalMaterial3Api::class)

package majestic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Border(
    val width: Dp,
    val color: Color,
    val shape: Shape
) {
    companion object {
        val Default = Border(
            width = 0.dp,
            color = Color.Transparent,
            shape = RoundedCornerShape(8.dp)
        )
    }
}

data class DropDownProperties(
    val border: Border,
    val background: Color,
    val shape: Shape,
    val shadowElevation: Dp,
    val tonalElevation: Dp
) {
    companion object {
        val Default = DropDownProperties(
            border = Border.Default,
            background = Color.Unspecified,
            shape = RoundedCornerShape(8.dp),
            shadowElevation = MenuDefaults.ShadowElevation,
            tonalElevation = MenuDefaults.TonalElevation
        )
    }
}

data class ContainerProperties(
    val border: Border,
    val background: Color,
    val shape: Shape
) {
    companion object {
        val Default = ContainerProperties(
            border = Border.Default,
            background = Color.Unspecified,
            shape = RoundedCornerShape(8.dp)
        )
    }
}

@Composable
fun <T> DumbSelect(
    items: Collection<T>,
    item: @Composable (T) -> Unit = { Text("$it") },
    selected: @Composable (T) -> Unit = item,
    value: T? = null,
    placeholder: @Composable () -> Unit = {
        Text("Select", modifier = Modifier.fillMaxWidth())
    },
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
                .background(color = dropDownContainerColor, shape = containerShape)
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
            modifier = Modifier.exposedDropdownSize().testTag("popup")
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> DumbSelect(
    items: Collection<T>,
    item: @Composable (T) -> Unit = { Text("$it") },
    selected: @Composable (T) -> Unit,
    value: T,
    onClick: ((T) -> Unit)? = null,
    onExpanded: ((Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier,
    dropdown: DropDownProperties = DropDownProperties.Default,
    container: ContainerProperties = ContainerProperties.Default
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
                .clip(shape = container.shape)
                .border(width = container.border.width, color = container.border.color, shape = container.shape)
                .background(color = container.background, shape = container.shape)
                .exposedDropdownSize()
                .menuAnchor(type = MenuAnchorType.PrimaryEditable)
                .clickable(
                    interactionSource = NoRippleInteractionSource,
                    indication = null,
                    onClick = {
                        expanded = true
                        onExpanded?.invoke(true)
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            selected(value)
        }
        ExposedDropdownMenu(
            containerColor = dropdown.background,
            shape = dropdown.shape,
            shadowElevation = dropdown.shadowElevation,
            border = null,
            tonalElevation = dropdown.tonalElevation,
            expanded = expanded,
            onDismissRequest = {
                expanded = false
                onExpanded?.invoke(false)
            },
            modifier = Modifier
                .border(
                    width = dropdown.border.width,
                    color = dropdown.border.color,
                    shape = dropdown.border.shape
                )
                .width(intrinsicSize = IntrinsicSize.Max).padding(8.dp)
                .testTag("popup")
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