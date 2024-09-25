package majestic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.format.toCssHex

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPicker(
    title: String,
    label: String,
    color: String?,
    modifier: Modifier = Modifier,
    onSelect: (String) -> Unit,
    onDismiss: () -> Unit
) {
    BasicAlertDialog(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .padding(vertical = 30.dp, horizontal = 20.dp),
        onDismissRequest = onDismiss
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var selectedColor by remember { mutableStateOf(color ?: "#000000") }
            var h by remember { mutableFloatStateOf(0f) }
            var s by remember { mutableFloatStateOf(0f) }
            var v by remember { mutableFloatStateOf(0f) }

            val colorState by remember(h, s, v) {
                derivedStateOf { Color.hsv(h, s, v) }
            }

            Text(
                text = title,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            SVColorCoordinate(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(20.dp)),
                hue = h,
                saturation = 0f,
                value = 0f,
                cueSize = 20.dp,
                onChange = { sat, va ->
                    s = sat
                    v = va
                    selectedColor = colorState.toCssHex()
                }
            )
            HueBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .clip(RoundedCornerShape(20.dp)),
                hue = h,
                cueSize = 14.dp,
                onChange = {
                    h = it
                    selectedColor = colorState.toCssHex()
                }
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = label)
                Spacer(modifier = Modifier.width(15.dp))
                PickerButton(
                    modifier = Modifier.width(200.dp),
                    pickerColor = selectedColor,
                    borderColor = Color.Black.copy(alpha = 0.2f),
                    onClick = {}
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ActionButton(
                    modifier = Modifier.weight(1f),
                    text = "Cancel",
                    interactionSource = NoRippleInteractionSource(),
                    border = BorderStroke(
                        width = 1.dp,
                        color = Color.Black.copy(alpha = 0.2f)
                    ),
                    colors = ButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    onClick = onDismiss
                )
                ActionButton(
                    modifier = Modifier.weight(1f),
                    text = "Select Color",
                    onClick = { onSelect(selectedColor) }
                )
            }
        }
    }
}
