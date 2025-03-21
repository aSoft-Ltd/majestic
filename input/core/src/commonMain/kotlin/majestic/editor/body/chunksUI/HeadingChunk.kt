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
import majestic.editor.toolbar.EditorColors


@Composable
internal fun HeadingChunk(
    heading: Heading,
    colors: EditorColors,
    labels: Labels
) = Box(modifier = Modifier.wrapContentSize().background(color = colors.background, shape = RoundedCornerShape(12.dp))) {

    fun getHintLevel(heading: Heading) = labels.getHeadingHint(heading)?.takeIf { heading.text.isBlank() } ?: heading.text

    var text by remember { mutableStateOf(heading.text) }

    BorderlessInput(
        value = text,
        hint = getHintLevel(heading),
        onChange = {
            text = it
            heading.text = it
        },
        colors = colors,
        modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 10.dp) //here measurement was 25 now its 10/15 but not much different if reduced farther it looks ugly
            .fillMaxWidth()
            .wrapContentHeight(), //this hugs the content and the look is not nice, the older design was not hugging the content than height of the box was  made it look like padding
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