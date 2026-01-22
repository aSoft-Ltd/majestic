package profiles.contacts.phone

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.Portrait
import composex.screen.orientation.ScreenOrientation
import majestic.ColorPair
import majestic.CountryDialingCodeSelector
import majestic.PhoneField
import majestic.PhoneFieldColors
import majestic.buttons.ActionButton
import majestic.buttons.ButtonColors
import majestic.icons.Res
import majestic.icons.ic_calling_solid
import majestic.icons.ic_info_circle
import majestic.icons.ic_whatsapp_solid
import nation.Country
import org.jetbrains.compose.resources.painterResource
import profiles.contacts.ContactForm
import tools.DialingCodePreview
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
        Row(
            modifier = Modifier
                .background(colors.info.copy(0.04f))
                .then(
                    other = when (orientation) {
                        is Landscape -> Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .padding(20.dp)

                        is Portrait -> Modifier
                            .fillMaxWidth()
                            .padding(15.dp)
                    }
                ),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Res.drawable.ic_info_circle),
                tint = colors.info,
                contentDescription = "Icon"
            )
            Text(
                text = labels.info,
                color = colors.info,
                fontSize = 15.sp
            )
        }

        val form = ContactForm()
        form.phone.setCountry(Country.TZ)
        PhoneField(
            field = form.phone,
            modifier = Modifier
                .then(if (orientation is Portrait) Modifier.padding(horizontal = 20.dp) else Modifier)
                .fillMaxWidth(),
            hint = labels.input.placeholder,
            colors = colors.phoneField,
            label = {
                Text(
                    modifier = Modifier.padding(bottom = 8.dp),
                    text = labels.input.label,
                    color = colors.label,
                )
            },
            leadingIcon = {
                CountryDialingCodeSelector(
                    modifier = Modifier.width(100.dp),
                    field = form.phone,
                    colors = colors.leadingIcon,
                    searchColors = colors.search,
                    selected = {
                        DialingCodePreview(
                            modifier = Modifier.padding(start = 10.dp),
                            country = it,
                            color = colors.codePreview
                        )
                    }
                )
            },
        )

        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
            var whatsapp by remember { mutableStateOf(false) }
            var normal by remember { mutableStateOf(false) }

            ButtonChoice(
                label = labels.availability.whatsapp,
                color = colors.button,
                selected = whatsapp,
                onClick = { whatsapp = !whatsapp }
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(Res.drawable.ic_whatsapp_solid),
                    tint = Color(0xFF25D366),
                    contentDescription = "Icon"
                )
            }
            ButtonChoice(
                label = labels.availability.calls,
                color = colors.button,
                selected = normal,
                onClick = { normal = !normal }
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(Res.drawable.ic_calling_solid),
                    tint = Color(0xFF30C0F9),
                    contentDescription = "Icon"
                )
            }
        }
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