package majestic.color

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import majestic.ActionButton
import majestic.ButtonColors
import majestic.NoRippleInteractionSource
import symphony.SubmittingPhase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPicker(
    pickerColor: String,
    colorLabel: String,
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
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = colorLabel)
                Spacer(modifier = Modifier.width(15.dp))
                PickerButton(
                    modifier = Modifier.width(200.dp),
                    pickerColor = pickerColor,
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
                    onClick = {}
                )
            }
        }
    }
}
