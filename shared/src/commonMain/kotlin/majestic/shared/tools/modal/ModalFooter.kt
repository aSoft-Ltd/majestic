package majestic.shared.tools.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.button.appearence.constructiveFormButton
import majestic.button.appearence.translucentFormButton
import majestic.button.appearence.translucentFormIconButton
import majestic.button.basic.FormButton

data class ModalFooterColors(
    val cancel: Color,
    val constructive: ColorPair,
    val destructive: ColorPair,
)

data class ModalFooterExtraButton(
    val label: String,
    val icon: ImageVector,
    val onClick: () -> Unit,
    val color: Color,
)

@Composable
fun ModalFooter(
    modifier: Modifier = Modifier,
    labels: ModalFooterLabels,
    colors: ModalFooterColors,
    isDestructive: Boolean = false,
    onSubmit: () -> Unit,
    onClose: () -> Unit
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
    verticalAlignment = Alignment.CenterVertically
) {
    FormButton(
        text = labels.secondary,
        modifier = Modifier.translucentFormButton(
            color = colors.cancel,
            onClick = onClose
        )
    )

    if (!isDestructive) {
        FormButton(
            text = labels.primary,
            modifier = Modifier.constructiveFormButton(
                colors = colors.constructive,
                onClick = onSubmit
            )
        )
    }
    else {
        FormButton(
            text = labels.primary,
            modifier = Modifier.constructiveFormButton(
                colors = colors.destructive,
                onClick = onSubmit
            )
        )
    }
}

@Composable
fun ModalFooter(
    modifier: Modifier = Modifier,
    labels: ModalFooterLabels,
    colors: ModalFooterColors,
    isDestructive: Boolean = false,
    extra: ModalFooterExtraButton,
    orientation: ScreenOrientation,
    onSubmit: () -> Unit,
    onClose: () -> Unit
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
) {
    when (orientation) {
        Landscape -> FormButton(
            icon = extra.icon,
            text = extra.label,
            modifier = Modifier.translucentFormButton(
                color = extra.color,
                onClick = extra.onClick
            )
        )

        Portrait -> FormButton(
            icon = extra.icon,
            modifier = Modifier.translucentFormIconButton(
                color = extra.color,
                onClick = extra.onClick
            )
        )
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End),
        verticalAlignment = Alignment.CenterVertically
    ) {
        FormButton(
            text = labels.secondary,
            modifier = Modifier.translucentFormButton(
                color = colors.cancel,
                onClick = onClose
            )
        )

        FormButton(
            text = labels.primary,
            modifier = Modifier.constructiveFormButton(
                colors = if (isDestructive) colors.destructive else colors.constructive,
                onClick = onSubmit
            )
        )
    }
}