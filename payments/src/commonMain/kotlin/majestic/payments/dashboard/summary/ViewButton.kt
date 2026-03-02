package majestic.payments.dashboard.summary

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.button.appearence.cardButton
import majestic.button.appearence.cardButtonMobile
import majestic.button.basic.CardButton

@Composable
internal fun ViewButton(
    label: String,
    color: Color,
    onClick: () -> Unit = {},
    orientation: ScreenOrientation,
) {
    when (orientation) {
        Landscape -> CardButton(
            text = label,
            modifier = Modifier.cardButton(
                color = color,
                onClick = onClick
            )
        )

        Portrait -> CardButton(
            modifier = Modifier.cardButtonMobile(
                color = color,
                onClick = onClick
            )
        )
    }
}