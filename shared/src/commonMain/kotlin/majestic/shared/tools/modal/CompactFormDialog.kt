package majestic.shared.tools.modal

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.button.appearence.closeModalIconButton
import majestic.button.appearence.constructiveFormButton
import majestic.button.basic.CloseModalButton
import majestic.button.basic.FormButton
import majestic.dialogs.flexible.FlexibleDialog

@Composable
fun CompactFormDialog(
    title: String,
    submit: String,
    onDismiss: () -> Unit,
    onSubmit: () -> Unit,
    orientation: ScreenOrientation,
    colors: ModalColors,
    submitColors: ColorPair,
    headerBackground: Color = colors.header,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) = FlexibleDialog(
    onDismiss = onDismiss,
    modifier = modifier,
    bar = {}
) {
    when (orientation) {
        is Landscape -> Box(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 52.dp, vertical = 28.dp),
                verticalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                Text(
                    text = title,
                    color = colors.text,
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    content = content
                )
                FormButton(
                    text = submit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constructiveFormButton(
                            colors = submitColors,
                            onClick = onSubmit
                        )
                )
            }
            CloseModalButton(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 14.dp, end = 16.dp)
                    .closeModalIconButton(
                        color = colors.closeButton,
                        onClick = onDismiss
                    )
            )
        }

        is Portrait -> Column(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(headerBackground)
                    .padding(20.dp)
            ) {
                Text(
                    text = title,
                    color = colors.text,
                    fontSize = 20.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.align(Alignment.Center)
                )
                CloseModalButton(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .closeModalIconButton(
                            color = colors.closeButton,
                            onClick = onDismiss
                        )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(28.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    content = content
                )
                FormButton(
                    text = submit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constructiveFormButton(
                            colors = submitColors,
                            onClick = onSubmit
                        )
                )
            }
        }
    }
}