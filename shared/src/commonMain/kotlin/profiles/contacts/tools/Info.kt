package profiles.contacts.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.icons.Res
import majestic.icons.ic_info_circle
import org.jetbrains.compose.resources.painterResource

internal fun Modifier.info(
    color: Color,
    orientation: ScreenOrientation
) = this
    .background(color.copy(0.04f))
    .then(
        other = when (orientation) {
            is Landscape -> Modifier
                .clip(RoundedCornerShape(10.dp))
                .padding(20.dp)

            is Portrait -> Modifier
                .fillMaxWidth()
                .padding(15.dp)
        }
    )

@Composable
internal fun Info(
    modifier: Modifier,
    color: Color,
    labels: String
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
    verticalAlignment = Alignment.CenterVertically
) {
    Icon(
        painter = painterResource(Res.drawable.ic_info_circle),
        tint = color,
        contentDescription = "Icon"
    )
    Text(
        text = labels,
        color = color,
        fontSize = 15.sp
    )
}