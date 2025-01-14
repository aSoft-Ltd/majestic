package majestic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.colors.ThemeColors

@Composable
fun Search(
    field: String,
    onChange: (String) -> Unit,
    theme: ThemeColors,
    modifier: Modifier,
    onSearch: () -> Unit = {},
    onFocusChange: (Boolean) -> Unit = {},
    onDismiss: () -> Unit = {},
    orientation: ScreenOrientation
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
) {
    val focusRequester = remember { FocusRequester() }
    BasicTextField(
        modifier = Modifier
            .weight(1f)
            .focusRequester(focusRequester)
            .onFocusChanged {
                onFocusChange(it.isFocused)
            },
        value = field,
        onValueChange = onChange,
        textStyle = TextStyle(
            fontSize = if (orientation == Portrait) 12.sp else 19.sp,
            color = theme.surface1.main.foreground,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        cursorBrush = SolidColor(theme.surface1.main.foreground),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        decorationBox = { innerTextField ->
            if (field.isEmpty()) {
                Text(
                    text = "Search Academia",
                    style = TextStyle(
                        fontSize = if (orientation == Portrait) 15.sp else 19.sp,
                        color = theme.surface1.main.foreground.copy(alpha = 0.4f),
                        lineHeight = 24.sp,
                        letterSpacing = 0.5.sp
                    )
                )
            }
            innerTextField()
        }
    )
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier.padding(bottom = 6.dp),
            text = "|",
            style = TextStyle(
                fontSize = 20.sp,
                color = theme.surface1.main.foreground.copy(.15f),
            )
        )
        IconButton(
            onClick = {
                onSearch()
                onDismiss()
            }
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = theme.surface1.main.foreground,
            )
        }
    }
}