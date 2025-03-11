package majestic.editor

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CoverPhoto(
    modifier: Modifier,
    coverPhoto: Painter,
    overLayIcon: ImageVector,
    changePhoto: () -> Unit,
    isHovered: Boolean,
    colors: ToolBarTabColors,
    instructions: String = "Change Photo",
    textStyle: TextStyle = TextStyle(
        fontSize = 10.sp,
        lineHeight = 28.8.sp,
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight(700)
    )
) = Box(modifier = modifier.clip(shape = RoundedCornerShape(8.dp))) {
    Image(
        painter = coverPhoto,
        contentDescription = "",
        modifier = Modifier.fillMaxSize()
    )
    if (isHovered) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colors.foreground.copy(.5f))
                .clickable(onClick = changePhoto),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(
                    space = 8.dp,
                    alignment = Alignment.CenterVertically
                )
            ) {
                Icon(
                    imageVector = overLayIcon,
                    contentDescription = "",
                    modifier = Modifier.size(20.dp),
                    tint = colors.foreground
                )
                Text(
                    text = instructions,
                    style = textStyle.copy(color = colors.text.active)
                )
            }
        }
    }
}