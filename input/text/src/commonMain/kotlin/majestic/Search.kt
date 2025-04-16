package majestic

import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.colors.ThemeColors

@Composable
fun Search(
    value: String,
    theme: ThemeColors,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "Search",
    focusRequester: FocusRequester? = null,
    textStyle: TextStyle? = null,
    hintStyle: TextStyle = LocalTextStyle.current,
    onSearch: () -> Unit = {},
    onFocusChange: (Boolean) -> Unit = {},
    onDismiss: () -> Unit = {},
    icon: @Composable (() -> Unit)? = null,
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
                .onSizeChanged { containerWidth = it.width },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BasicTextField(
                modifier = if (focusRequester != null) {
                    Modifier.focusRequester(focusRequester)
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
                    color = theme.surface1.main.foreground
                ),
                cursorBrush = SolidColor(theme.surface1.main.foreground),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                decorationBox = { innerTextField ->
                    if (value.isEmpty()) Text(
                        text = hint,
                        color = theme.surface1.main.foreground.copy(alpha = 0.5f),
                        style = hintStyle
                    )
                    innerTextField()
                },
                singleLine = true
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                VerticalDivider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    color = theme.surface1.main.foreground.copy(alpha = 0.15f)
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
                        tint = theme.surface1.main.foreground,
                    )
                }
            }
        }

        suggestions(isFocused, containerWidth)
    }
}