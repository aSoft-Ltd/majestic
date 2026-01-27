package majestic.payments.tools.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ColorPair
import majestic.tooling.onClick

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
    Text(
        modifier = Modifier.clip(CircleShape)
            .background(colors.cancel.background)
            .pointerHoverIcon(PointerIcon.Hand)
            .onClick(onCancel)
            .padding(vertical = 10.dp, horizontal = 20.dp),
        text = cancel,
        fontSize = 14.sp,
        lineHeight = 1.sp,
        maxLines = 1,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis,
        color = colors.cancel.foreground
    )
    Text(
        modifier = Modifier.clip(CircleShape)
            .background(colors.submit.background)
            .pointerHoverIcon(PointerIcon.Hand)
            .onClick(onSubmit)
            .padding(vertical = 10.dp, horizontal = 20.dp),
        text = submit,
        fontSize = 14.sp,
        lineHeight = 1.sp,
        maxLines = 1,
        textAlign = TextAlign.Center,
        overflow = TextOverflow.Ellipsis,
        color = colors.submit.foreground
    )
}
