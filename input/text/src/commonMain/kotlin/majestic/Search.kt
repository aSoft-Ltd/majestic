package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import majestic.colors.ThemeColors

enum class Type {
    USER,
    FEATURE,
    SETTING,
    DATA
}

data class Item(val text: String, val type: Type)

val items = listOf(
    Item("John Doe", Type.USER),
    Item("HeroSection", Type.FEATURE),
    Item("Physics", Type.SETTING),
    Item("Mathematics", Type.SETTING),
    Item("Chemistry", Type.SETTING),
    Item("Biology", Type.SETTING),
    Item("Web Management", Type.FEATURE),
    Item("Academics", Type.FEATURE),
    Item("All Students", Type.DATA)
)

@Composable
fun Search(
    field: String,
    theme: ThemeColors,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = "Search",
    textStyle: TextStyle? = null,
    hintStyle: TextStyle = LocalTextStyle.current,
    onSearch: () -> Unit = {},
    onFocusChange: (Boolean) -> Unit = {},
    onDismiss: () -> Unit = {},
    icon: @Composable (() -> Unit)? = null,
    items: List<Item> = emptyList(),
    onSuggestionSelected: (Item) -> Unit = {}
) {
    var isFocused by remember { mutableStateOf(false) }
    var filteredSuggestions by remember { mutableStateOf<List<Item>>(emptyList()) }
    var rowWidth by remember { mutableStateOf(0) }
    val density = LocalDensity.current

    LaunchedEffect(field) {
        filteredSuggestions = if (field.isNotEmpty()) {
            items.filter { item ->
                item.text.contains(field, ignoreCase = true)
            }
        } else {
            emptyList()
        }
    }

    Box(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .onSizeChanged { rowWidth = it.width },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val focusRequester = remember { FocusRequester() }
            BasicTextField(
                modifier = Modifier
                    .weight(1f)
                    .focusRequester(focusRequester)
                    .onFocusChanged {
                        isFocused = it.isFocused
                        onFocusChange(it.isFocused)
                    },
                value = field,
                onValueChange = onChange,
                textStyle = textStyle ?: LocalTextStyle.current.copy(
                    fontSize = 16.sp,
                    color = theme.surface1.main.foreground
                ),
                cursorBrush = SolidColor(theme.surface1.main.foreground),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                decorationBox = { innerTextField ->
                    if (field.isEmpty()) {
                        Text(
                            text = hint,
                            fontSize = 16.sp,
                            color = theme.surface1.main.foreground.copy(alpha = 0.5f),
                            style = hintStyle
                        )
                    }
                    innerTextField()
                }
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

        DropdownMenu(
            containerColor = theme.surface1.main.background,
            expanded = isFocused && filteredSuggestions.isNotEmpty(),
            onDismissRequest = { /* Do nothing to prevent auto-dismiss */ },
            modifier = Modifier
                .width(with(density) { rowWidth.toDp() })
                .background(theme.surface1.main.background),
            properties = PopupProperties(
                focusable = false,
                dismissOnBackPress = false,
                dismissOnClickOutside = true
            )
        ) {
            filteredSuggestions.forEach { item ->
                DropdownMenuItem(
                    modifier = Modifier
                        .width(with(density) { rowWidth.toDp() })
                        .background(theme.surface1.main.background),
                    onClick = {
                        onSuggestionSelected(item)
                        onChange(item.text)
                    },
                    text = {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = item.text,
                                color = theme.surface1.main.foreground
                            )
                            Text(
                                text = item.type.name,
                                color = theme.surface1.main.foreground.copy(alpha = 0.6f),
                                fontSize = 14.sp
                            )
                        }
                    }
                )
            }
        }
    }
}