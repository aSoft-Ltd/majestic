package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.format.fromHex

@Composable
fun PickerValue(
    pickerColor: String,
    onValueChange: (String) -> Unit = { },
    modifier: Modifier = Modifier,
    textColor: Color = Color.Unspecified,
    backgroundColor: Color = Color.Unspecified
) = Row(
    modifier = modifier
        .clip(RoundedCornerShape(3.dp))
        .background(color = backgroundColor)
        .padding(4.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(10.dp)
) {
    Box(
        modifier = Modifier
            .size(28.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(color = pickerColor.toPickerColor())
    )
    BasicTextField(
        value = pickerColor,
        onValueChange = onValueChange,
        modifier = Modifier.weight(1f),
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 12.sp,
            color = textColor
        ),
        cursorBrush = SolidColor(textColor)
    )
}

private fun String.toPickerColor() = runCatching { Color.fromHex(this) }.getOrDefault(Color.Transparent)