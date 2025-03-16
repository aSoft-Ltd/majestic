package majestic.editor.body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.editor.body.chunkUI.BorderColors
import majestic.editor.body.chunkUI.DropDownColors
import majestic.editor.body.chunkUI.IconedSelector
import majestic.editor.body.chunkUI.TextColors
import majestic.editor.body.chunkUI.TextField
import majestic.editor.body.chunks.EditorControl
import majestic.editor.insert.Insert
import majestic.editor.toolbar.EditorColors

@Composable
internal fun ChunkWrapper(
    modifier: Modifier = Modifier,
    colors: EditorColors,
    control: EditorControl,
    customItemContent: @Composable (Insert) -> Unit,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit,
    content: @Composable () -> Unit
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(14.dp, alignment = Alignment.Top)
) {
    val selected = control.dropDowns.selected

    Row(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier
                .wrapContentWidth()
                .padding(end = 20.dp, top = 8.dp, bottom = 8.dp)
                .background(color = colors.background, shape = RoundedCornerShape(30.dp)),
            horizontalArrangement = Arrangement.spacedBy(
                space = 20.dp,
                alignment = Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            control.actions.inserts.forEach { action ->
                action.content()
            }
        }

        selected?.let { it ->
            IconedSelector(
                modifier = Modifier.height(50.dp).wrapContentWidth(),
                options = control.dropDowns.inserts,
                selectedItem = it,
                onItemChange = {
                    control.dropDowns.select(it)
                },
                colors = DropDownColors(
                    background = colors.background,
                    field = TextField(
                        text = TextColors(
                            focused = colors.foreground,
                            unfocused = colors.foreground.copy(alpha = 0.5f)
                        ),
                        border = BorderColors(
                            focused = colors.foreground,
                            unfocused = colors.foreground.copy(alpha = 0.5f)
                        )
                    )
                ),
                customItemContent = customItemContent,
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                shape = RoundedCornerShape(30.dp),
                textStyle = TextStyle(
                    color = colors.foreground,
                    fontSize = 14.sp
                )
            )
        }
    }

    Box(modifier = Modifier.wrapContentSize().background(color = colors.background, shape = RoundedCornerShape(12.dp))) {
        content()
    }
}