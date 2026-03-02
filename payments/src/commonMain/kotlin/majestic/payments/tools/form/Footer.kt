package majestic.payments.tools.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.ColorPair
import majestic.button.appearence.constructiveFormButton
import majestic.button.appearence.translucentFormButton
import majestic.button.basic.FormButton

data class FormFooterColors(
    val cancel: ColorPair,
    val submit: ColorPair
)

@Composable
internal fun Footer(
    colors: FormFooterColors,
    submit: String,
    cancel: String,
    onSubmit: () -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.spacedBy(space = 8.dp, alignment = Alignment.End)
) {
    FormButton(
        text = cancel,
        modifier = Modifier.translucentFormButton(
            color = colors.cancel.foreground,
            onClick = onCancel
        )
    )

    FormButton(
        text = submit,
        modifier = Modifier.constructiveFormButton(
            colors = colors.submit,
            onClick = onSubmit
        )
    )
}