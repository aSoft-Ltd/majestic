package profiles.contacts.phone.form

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composex.screen.orientation.ScreenOrientation
import Flex
import majestic.icons.Res
import majestic.icons.ic_calling_solid
import majestic.icons.ic_whatsapp_solid
import org.jetbrains.compose.resources.painterResource
import profiles.contacts.phone.ButtonChoice
import profiles.contacts.phone.PhoneFormColors
import users.label.contacts.DedicatedFormLabels


@Composable
internal fun ButtonsRow(
    modifier: Modifier,
    orientation: ScreenOrientation,
    labels: DedicatedFormLabels,
    colors: PhoneFormColors
) = Flex(
    horizontalArrangement = Arrangement.spacedBy(20.dp),
    modifier = modifier,
    orientation = orientation,
    verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
    alignment = Alignment.CenterVertically
) {
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