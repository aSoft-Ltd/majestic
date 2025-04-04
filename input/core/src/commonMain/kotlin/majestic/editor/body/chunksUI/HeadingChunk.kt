package majestic.editor.body.chunksUI

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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
import majestic.editor.body.chunksUI.tools.Labels
import majestic.editor.tools.EditorColors

private fun getHint(heading: Heading, labels: Labels) = labels.getHeadingHint(heading)?.takeIf { heading.text.isBlank() } ?: heading.text

@Composable
internal fun HeadingChunk(
    heading: Heading,
    colors: EditorColors,
    labels: Labels
) = Box(modifier = Modifier.wrapContentSize().background(color = colors.background, shape = RoundedCornerShape(12.dp))) {

    var text by remember { mutableStateOf(heading.text) }

    BorderlessInput(
        value = text,
        hint = getHint(heading, labels),
        onChange = {
            text = it
            heading.text = it
        },
        colors = colors,
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        style = TextStyle(
            fontSize = when (heading.level) {
                1 -> 32.sp
                2 -> 28.sp
                3 -> 24.sp
                else -> 20.sp
            },
            lineHeight = 46.sp
        )
    )
}