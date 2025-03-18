package majestic.editor.body.chunksUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.editor.body.chunks.Paragraph
import majestic.editor.toolbar.EditorColors

@Composable
internal fun ParagraphChunk(
    paragraph: Paragraph,
    colors: EditorColors,
) = Box(
    modifier = Modifier
        .wrapContentSize()
        .background(color = colors.background, shape = RoundedCornerShape(12.dp))
) {
    var text by remember { mutableStateOf(paragraph.text) }

    TextField(
        value = text,
        onValueChange = {
            text = it
            paragraph.text = it
        },
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(color = colors.background, shape = RoundedCornerShape(12.dp))
            .padding(25.dp)
            .fillMaxWidth()
            .height(350.dp),
        textStyle = TextStyle(
            background = colors.background,
            fontSize = 16.sp,
            lineHeight = 30.sp,
            color = colors.text.active,
            fontWeight = FontWeight(500)
        ),
        singleLine = false,
        minLines = 200,
        colors = colors.toTextFieldColors()
    )
}

private fun EditorColors.toTextFieldColors() = TextFieldColors(
    textSelectionColors = TextSelectionColors(
        handleColor = text.active,
        backgroundColor = background
    ),
    disabledTextColor = Color.Transparent,
    errorTextColor = Color.Transparent,
    disabledContainerColor = Color.Transparent,
    errorContainerColor = Color.Transparent,
    errorCursorColor = Color.Transparent,
    disabledIndicatorColor = Color.Transparent,
    errorIndicatorColor = Color.Transparent,
    focusedLeadingIconColor = Color.Transparent,
    unfocusedLeadingIconColor = Color.Transparent,
    disabledLeadingIconColor = Color.Transparent,
    errorLeadingIconColor = Color.Transparent,
    focusedTrailingIconColor = Color.Transparent,
    unfocusedTrailingIconColor = Color.Transparent,
    disabledTrailingIconColor = Color.Transparent,
    errorTrailingIconColor = Color.Transparent,
    focusedLabelColor = Color.Transparent,
    unfocusedLabelColor = Color.Transparent,
    disabledLabelColor = Color.Transparent,
    errorLabelColor = Color.Transparent,
    focusedPlaceholderColor = background,
    unfocusedPlaceholderColor = background,
    disabledPlaceholderColor = Color.Transparent,
    errorPlaceholderColor = Color.Transparent,
    focusedSupportingTextColor = Color.Transparent,
    unfocusedSupportingTextColor = Color.Transparent,
    disabledSupportingTextColor = Color.Transparent,
    errorSupportingTextColor = Color.Transparent,
    focusedPrefixColor = Color.Transparent,
    unfocusedPrefixColor = Color.Transparent,
    disabledPrefixColor = Color.Transparent,
    errorPrefixColor = Color.Transparent,
    focusedSuffixColor = Color.Transparent,
    unfocusedSuffixColor = Color.Transparent,
    disabledSuffixColor = Color.Transparent,
    errorSuffixColor = Color.Transparent,
    focusedTextColor = text.active,
    unfocusedTextColor = text.inActive,
    focusedContainerColor = background,
    unfocusedContainerColor = background,
    cursorColor = text.active,
    focusedIndicatorColor = background,
    unfocusedIndicatorColor = background
)