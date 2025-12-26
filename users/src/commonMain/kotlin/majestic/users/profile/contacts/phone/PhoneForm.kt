package majestic.users.profile.contacts.phone

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.ActionButton
import majestic.ButtonColors
import majestic.ColorPair
import majestic.CountryDialingCodeSelector
import majestic.PhoneField
import majestic.PhoneFieldColors
import majestic.ThemeColor
import majestic.users.labels.profile.contact.DedicatedFormLabels
import majestic.users.profile.contacts.ContactForm
import majestic.users.tools.DialingCodePreview
import nation.Country
import org.jetbrains.compose.resources.painterResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_calling_solid
import tz.co.asoft.majestic_users.generated.resources.ic_info_circle
import tz.co.asoft.majestic_users.generated.resources.ic_whatsapp_solid

data class PhoneFormColors(
    val theme: ThemeColor,
    val popMainColors: ColorPair,
    val phoneField: PhoneFieldColors
)

@Composable
internal fun PhoneForm(
    colors: PhoneFormColors,
    labels: DedicatedFormLabels,
    onSubmit: () -> Unit,
    modifier: Modifier = Modifier
) = Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(30.dp),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    val theme = colors.theme
    val popMainColors = colors.popMainColors
    Text(
        text = labels.title,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp,
        color = theme.surface.contra.color
    )
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFE5A134).copy(0.04f))
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_info_circle),
            tint = Color(0xFFE5A134),
            contentDescription = "Icon"
        )
        Text(
            text = labels.info,
            color = Color(0xFFE5A134),
        )
    }

    val form = ContactForm()
    form.phone.setCountry(Country.TZ)
    PhoneField(
        field = form.phone,
        modifier = Modifier.fillMaxWidth(),
        hint = labels.input.placeholder,
        colors = colors.phoneField,
        label = {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = labels.input.label,
                color = theme.surface.contra.color.copy(alpha = 0.7f)
            )
        },
        leadingIcon = {
            CountryDialingCodeSelector(
                modifier = Modifier.width(100.dp),
                field = form.phone,
                colors = ColorPair(
                    background = popMainColors.background,
                    foreground = popMainColors.foreground
                ),
                searchColors = ColorPair(
                    background = theme.surface.actual.color,
                    foreground = popMainColors.foreground
                ),
                selected = {
                    DialingCodePreview(
                        modifier = Modifier.padding(start = 10.dp),
                        country = it,
                        color = theme.surface.contra.color
                    )
                }
            )
        },
    )

    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        var whatsapp by remember { mutableStateOf(false) }
        var normal by remember { mutableStateOf(false) }
        val buttonColors = ColorPair(
            foreground = popMainColors.foreground,
            background = popMainColors.background
        )
        ButtonChoice(
            label = labels.availability.whatsapp,
            color = buttonColors,
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
            color = buttonColors,
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

    ActionButton(
        modifier = Modifier.fillMaxWidth(),
        text = labels.submit,
        colors = ButtonColors(
            contentColor = theme.surface.actual.color,
            containerColor = theme.surface.contra.color
        ),
        onClick = onSubmit
    )
}