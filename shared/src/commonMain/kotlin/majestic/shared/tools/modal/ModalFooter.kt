package majestic.shared.tools.modal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import majestic.ColorPair
import majestic.button.appearence.constructiveFormButton
import majestic.button.appearence.translucentFormButton
import majestic.button.basic.FormButton

data class ModalFooterColors(
    val cancel: Color,
    val constructive: ColorPair,
    val destructive: ColorPair,
)

@Composable
fun ModalFooter(
    modifier: Modifier = Modifier.Companion,
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