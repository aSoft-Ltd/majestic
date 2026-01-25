package profiles.contacts.phone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.PhoneFieldColors
import majestic.buttons.ActionButton
import majestic.buttons.ButtonColors
import profiles.contacts.phone.form.ButtonsRow
import profiles.contacts.phone.form.Form
import profiles.contacts.tools.Info
import profiles.contacts.tools.info
import users.label.contacts.DedicatedFormLabels

data class PhoneFormColors(
    val info: Color,
    val label: Color,
    val leadingIcon: ColorPair,
    val search: ColorPair,
    val codePreview: Color,
    val button: ColorPair,
    val phoneField: PhoneFieldColors,
    val action: ButtonColors,
)

@Composable
fun PhoneForm(
    colors: PhoneFormColors,
    labels: DedicatedFormLabels,
    onSubmit: () -> Unit,
    orientation: ScreenOrientation,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = if (orientation is Landscape) Arrangement.spacedBy(30.dp) else Arrangement.SpaceBetween,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Info(
            modifier = Modifier.info(color = colors.info, orientation = orientation),
            color = colors.info,
            labels = labels.info
        )

        Form(
            modifier = Modifier
                .then(
                    other = when (orientation) {
                        is Portrait -> Modifier.padding(horizontal = 20.dp)
                        is Landscape -> Modifier
                    }
                )
                .fillMaxWidth(),
            labels = labels,
            colors = colors
        )

        ButtonsRow(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            orientation = orientation,
            labels = labels,
            colors = colors
        )
    }
    ActionButton(
        modifier = Modifier
            .then(if (orientation is Portrait) Modifier.padding(horizontal = 20.dp) else Modifier)
            .fillMaxWidth(),
        text = labels.submit,
        colors = colors.action,
        onClick = onSubmit
    )
}