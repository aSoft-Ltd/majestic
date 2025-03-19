package majestic.editor

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.editor.toolbar.EditorColors

class EmojiPreservingVisualTransformation(private val textColor: Color) : VisualTransformation {
    private val emojiRegex = Regex("[\uD83C-\uDBFF\uDC00-\uDFFF]+")

    override fun filter(text: AnnotatedString): TransformedText {
        if (text.isEmpty()) return TransformedText(text, OffsetMapping.Identity)

        val result = buildAnnotatedString {
            var lastIndex = 0

            emojiRegex.findAll(text.text).forEach { match ->
                if (match.range.first > lastIndex) {
                    val textSegment = text.text.substring(lastIndex, match.range.first)
                    withStyle(androidx.compose.ui.text.SpanStyle(color = textColor)) {
                        append(textSegment)
                    }
                }

                append(match.value)

                lastIndex = match.range.last + 1
            }
            if (lastIndex < text.length) {
                val textSegment = text.text.substring(lastIndex)
                withStyle(androidx.compose.ui.text.SpanStyle(color = textColor)) {
                    append(textSegment)
                }
            }
        }
        return TransformedText(result, OffsetMapping.Identity)
    }
}

@Composable
fun BorderlessInput(
    modifier: Modifier = Modifier,
    value: String,
    hint: String = "",
    onChange: (String) -> Unit,
    colors: EditorColors,
    singleLine: Boolean = true,
    minLines: Int = 1,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    style: TextStyle = TextStyle(
        lineHeight = 24.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(600),
    ),
    preserveEmojiColors: Boolean = true
) {
    var focusState by remember { mutableStateOf(false) }
    val lineHeight = style.lineHeight.value.dp
    val textColor = if (focusState) colors.text.active else colors.text.inActive

    BasicTextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier
            .onFocusChanged { focusState = it.isFocused }
            .heightIn(min = lineHeight * minLines, max = if (singleLine) lineHeight else lineHeight * maxLines),
        singleLine = singleLine,
        textStyle = style.copy(
            color = if (!preserveEmojiColors) textColor else Color.Unspecified
        ),
        cursorBrush = SolidColor(colors.text.active),
        interactionSource = interactionSource,
        visualTransformation = if (preserveEmojiColors) {
            EmojiPreservingVisualTransformation(textColor)
        } else {
            VisualTransformation.None
        },
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent),
                contentAlignment = if (singleLine || !focusState) Alignment.CenterStart else Alignment.TopStart
            ) {
                if (value.isEmpty() && !focusState) {
                    Text(
                        text = hint,
                        style = style.copy(color = colors.text.inActive),
                    )
                } else {
                    innerTextField()
                }
            }
        }
    )
}