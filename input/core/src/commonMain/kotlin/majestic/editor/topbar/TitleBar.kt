package majestic.editor.topbar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.editor.BorderlessInput
import majestic.editor.toolbar.ToolBarTabColors

@Composable
fun TitleBar(
    modifier: Modifier = Modifier.fillMaxWidth(),
    title: String = "Untitled",
    onTitleChange: (String) -> Unit,
    onBack: () -> Unit = {},
    resource: Painter,
    colors: ToolBarTabColors,
    style: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 14.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(600)
    )
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start)
) {
    Icon(
        painter = resource,
        tint = colors.foreground,
        contentDescription = "",
        modifier = Modifier.size(30.dp).clickable(onClick = onBack)
    )

    BorderlessInput(
        modifier = Modifier
            .weight(1f)
            .height(36.dp)
            .padding(4.dp),
        value = title,
        onChange = onTitleChange,
        colors = colors,
        style = style
    )
}