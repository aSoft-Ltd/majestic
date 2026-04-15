package majestic.shared.progressIndicator

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import majestic.ThemeColor

@Composable
fun Stage(
    title: String,
    theme: ThemeColor,
    isDone: Boolean,
    textAlign: TextAlign = TextAlign.Center
) = Text(
    text = title,
    color = if (isDone) theme.surface.contra.color
    else theme.surface.contra.color.copy(alpha = .55f),
    fontSize = 13.sp,
    textAlign = textAlign,
    maxLines = 1
)