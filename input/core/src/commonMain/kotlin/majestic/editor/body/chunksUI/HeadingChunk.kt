package majestic.editor.body.chunksUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.editor.BorderlessInput
import majestic.editor.body.chunks.Heading
import majestic.editor.toolbar.EditorColors

@Composable
internal fun HeadingChunk(
    heading: Heading,
    colors: EditorColors,
) = Box(modifier = Modifier.wrapContentSize().background(color = colors.background, shape = RoundedCornerShape(12.dp))) {

    val hint by remember {
        val preAssigned = when (heading.level) {
            1 -> "Heading 1"
            2 -> "Heading 2"
            3 -> "Heading 3"
            else -> null
        }
        mutableStateOf(
            if (preAssigned != null && heading.text == "") preAssigned else heading.text
        )

    }

    var text by remember { mutableStateOf(heading.text) }

    BorderlessInput(
        value = text,
        hint = hint,
        onChange = {
            text = it
            heading.text = it
        },
        colors = colors,
        modifier = Modifier.padding(25.dp).fillMaxWidth().height(100.dp),
        style = TextStyle(
            fontSize = when (heading.level) {
                1 -> 32.sp
                2 -> 28.sp
                3 -> 24.sp
                else -> 20.sp
            }
        )
    )
}