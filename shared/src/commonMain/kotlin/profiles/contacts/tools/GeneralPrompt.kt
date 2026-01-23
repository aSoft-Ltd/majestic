package profiles.contacts.tools

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.buttons.ActionButton
import majestic.buttons.ButtonColors
import org.jetbrains.compose.resources.painterResource
import majestic.icons.Res
import majestic.icons.ic_info_circle

data class GeneralPromptColors(
    val title: Color,
    val description: Color,
    val contact: Color,
    val warning: Color = Color(0xFFE5A134),
    val buttonBorder: Color,
    val cancel: ButtonColors,
    val submit: ButtonColors
)

internal data class GeneralPromptLabels(
    val title: String,
    val description: String,
    val contact: String,
    val info: String,
    val submit: String,
    val cancel: String
)

@Composable
internal fun GeneralPrompt(
    colors: GeneralPromptColors,
    labels: GeneralPromptLabels,
    onRejected: () -> Unit,
    orientation: ScreenOrientation,
    contact: String? = null,
    modifier: Modifier = Modifier,
    onApproved: () -> Unit = {},
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(30.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Text(
        text = labels.title,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = colors.title
    )
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = labels.description,
            color = colors.description,
            textAlign = if (orientation is Landscape) TextAlign.Start else TextAlign.Center
        )
        Text(
            text = contact ?: "",
            fontWeight = FontWeight.Bold,
            color = colors.contact,
            textAlign = if (orientation is Landscape) TextAlign.Start else TextAlign.Center
        )
    }
    Row(
        modifier = Modifier
            .then(if (orientation is Portrait) Modifier.fillMaxWidth() else Modifier)
            .clip(RoundedCornerShape(10.dp))
            .background(color = colors.warning.copy(0.04f), shape = RoundedCornerShape(10.dp))
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_info_circle),
            tint = colors.warning,
            contentDescription = "Icon"
        )
        Text(
            text = labels.info,
            color = colors.warning,
            fontSize = if (orientation is Landscape) 15.sp else 14.sp
        )
    }
    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        ActionButton(
            modifier = Modifier.weight(1f),
            text = labels.submit,
            colors = colors.submit,
            onClick = onApproved
        )
        ActionButton(
            modifier = Modifier.weight(1f),
            text = labels.cancel,
            border = BorderStroke(
                width = 1.dp,
                color = colors.buttonBorder.copy(alpha = 0.2f)
            ),
            colors = colors.cancel,
            onClick = onRejected
        )
    }
}