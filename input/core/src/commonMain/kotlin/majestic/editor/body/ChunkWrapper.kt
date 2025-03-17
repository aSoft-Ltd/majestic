package majestic.editor.body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.editor.body.chunkUI.DropDownColors
import majestic.editor.body.chunkUI.FieldColors
import majestic.editor.body.chunkUI.IconedSelector
import majestic.editor.body.chunkUI.StateColors
import majestic.editor.body.chunks.EditorControl
import majestic.editor.insert.Insert
import majestic.editor.toolbar.EditorColors

@Composable
internal fun ChunkWrapper(
    modifier: Modifier = Modifier,
    colors: EditorColors,
    control: EditorControl,
    actions: @Composable () -> Unit,
    customItemContent: @Composable (Insert) -> Unit,
    leadingIcon: @Composable () -> Unit,
    trailingIcon: @Composable () -> Unit,
    content: @Composable () -> Unit
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Top)
) {
    val selected = control.dropDowns.selected

    Row(
        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        actions()

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
                    field = FieldColors(
                        text = StateColors(
                            focused = colors.foreground,
                            unfocused = colors.foreground.copy(alpha = 0.5f)
                        ),
                        border = StateColors(
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