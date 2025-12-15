package majestic.users.dashboard.table.header.tools.filters

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
internal fun InlinedTextField(
    value: String,
    hint: String = "",
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    readOnly: Boolean = false,
    cursor: Color = Color.Black,
    onFocusChange: (Boolean) -> Unit = {},
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    textStyle: TextStyle = LocalTextStyle.current,
    hintStyle: TextStyle = LocalTextStyle.current,
    onChange: (String) -> Unit,
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
) {
    var text by remember { mutableStateOf(value) }
    leadingIcon?.invoke()
    BasicTextField(
        modifier = Modifier.weight(1f)
            .wrapContentHeight()
            .padding(contentPadding)
            .onFocusChanged { onFocusChange(it.isFocused) },
        value = text,
        singleLine = true,
        readOnly = readOnly,
        textStyle = textStyle,
        cursorBrush = SolidColor(cursor),
        onValueChange = {
            text = it
            onChange(it)
        },
        decorationBox = { innerTextField ->
            if (value.isEmpty()) Text(
                modifier = Modifier.padding(contentPadding),
                text = hint,
                style = hintStyle,
            )
            innerTextField()
        },
    )
    trailingIcon?.invoke()
}
