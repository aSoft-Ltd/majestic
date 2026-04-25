package majestic.button.basic

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import majestic.button.Button
import majestic.icons.Res
import majestic.icons.ic_angle_right
import org.jetbrains.compose.resources.vectorResource

@Composable
fun CardButton(
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 14.sp,
    modifier: Modifier = Modifier
) = Button(modifier = modifier) { colors ->
        BasicButtonContent(
            text = text,
            fontWeight = fontWeight,
            fontSize = fontSize,
            alpha = 0.7f,
            colors = colors
        )
    }

// mobile overload
@Composable
fun CardButton(
    icon: ImageVector = vectorResource(Res.drawable.ic_angle_right),
    modifier: Modifier = Modifier
) = Button(modifier = modifier) { colors ->
        BasicButtonContent(
            icon = icon,
            colors = colors
        )
    }