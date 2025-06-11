package majestic

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.editor.tools.StateColors

data class SearchDefaultColors(
    val hint: Color,
    val text: Color,
    val cursor: Color,
    val border: StateColors,
    val verticalDivider: Color,
    val tint: Color
) {
    companion object {
        val Default = SearchDefaultColors(
            hint = Color(0xFFB0B0B0),
            text = Color(0xFF000000),
            cursor = Color(0xFF000000),
            border = StateColors(
                focused = Color(0xFFFFFFFF),
                unfocused = Color(0xFFB0B0B0)
            ),
            verticalDivider = Color(0xFFB0B0B0),
            tint = Color(0xFF000000)
        )
    }
}

@Composable
fun Search(
    value: String,
    colors: SearchDefaultColors = SearchDefaultColors.Default,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(30.dp),
    hint: String = "Search",
    focusRequester: FocusRequester? = null,
    textStyle: TextStyle? = null,
    hintStyle: TextStyle = LocalTextStyle.current,
    onSearch: () -> Unit = {},
    onFocusChange: (Boolean) -> Unit = {},
    onDismiss: () -> Unit = {},
    icon: @Composable() (() -> Unit)? = null,
    suggestions: @Composable (Boolean, Int) -> Unit = { _, _ -> },
    onEnter: (keyEvent: KeyEvent) -> Unit = { },
    onUpArrow: () -> Unit = {},
    onDownArrow: () -> Unit = {},
) {
    var isFocused by remember { mutableStateOf(false) }
    var containerWidth by remember { mutableStateOf(0) }


    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = if (isFocused) colors.border.focused else colors.border.unfocused, shape = shape)
                .onSizeChanged { containerWidth = it.width },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BasicTextField(
                modifier = if (focusRequester != null) {
                    Modifier.focusRequester(focusRequester).padding(start = 10.dp)
                } else {
                    Modifier
                }.weight(1f)
                    .onFocusChanged {
                        isFocused = it.isFocused
                        onFocusChange(it.isFocused)
                    }.onPreviewKeyEvent {
                        when {
                            it.key == Key.DirectionUp && it.type == KeyEventType.KeyUp -> {
                                onUpArrow()
                                true
                            }

                            it.key == Key.DirectionDown && it.type == KeyEventType.KeyUp -> {
                                onDownArrow()
                                true
                            }

                            else -> false
                        }
                    }
                    .onKeyEvent {
                        if (it.key != Key.Enter) return@onKeyEvent false
                        onEnter(it)
                        true
                    },
                value = value,
                onValueChange = onChange,
                textStyle = textStyle ?: LocalTextStyle.current.copy(
                    fontSize = 16.sp,
                    color = colors.text
                ),
                cursorBrush = SolidColor(colors.cursor),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = hint,
                        color = colors.hint,
                        style = hintStyle
                    )
                    innerTextField()
                },
                singleLine = true
            )


            Row(verticalAlignment = Alignment.CenterVertically) {
                VerticalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = colors.verticalDivider
                )
                IconButton(
                    onClick = {
                        onSearch()
                        onDismiss()
                    },
                    modifier = Modifier.focusProperties {
                        canFocus = false
                    }
                ) {
                    if (icon != null) icon() else Icon(
                        modifier = Modifier.padding(10.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon",
                        tint = colors.tint,
                    )
                }
            }
        }

        suggestions(isFocused, containerWidth)
    }
}