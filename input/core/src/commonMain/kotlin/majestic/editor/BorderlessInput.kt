package majestic.editor


import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import majestic.editor.toolbar.ToolBarTabColors

@Composable
fun BorderlessInput(
    modifier: Modifier,
    value: String,
    onChange: (String) -> Unit,
    colors: ToolBarTabColors,
    singleLine: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    style: TextStyle = TextStyle(
        lineHeight = 14.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(600),
    )
) {
    var focusState by remember { mutableStateOf(false) }
    BasicTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier.onFocusChanged { focusState = it.isFocused },
        singleLine = singleLine,
        textStyle = style.copy(
            color = if (focusState) colors.text.active else colors.text.inActive
        ),
        cursorBrush = SolidColor(colors.text.active),
        interactionSource = interactionSource,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                contentAlignment = Alignment.CenterStart
            ) {
                innerTextField()
            }
        }
    )
}