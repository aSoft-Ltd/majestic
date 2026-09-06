package majestic.shared.profiles.contacts.phone

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.icons.Res
import majestic.icons.ic_calling_solid
import majestic.icons.ic_whatsapp_solid
import majestic.shared.users.label.contacts.DedicatedFormLabels
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun PhoneAvailabilityFields(
    state: PhoneFormState,
    labels: DedicatedFormLabels,
    colors: PhoneFormColors,
    modifier: Modifier = Modifier
) = Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
    verticalAlignment = Alignment.CenterVertically
) {
    ButtonChoice(
        label = labels.availability.whatsapp,
        color = colors.button,
        selected = state.whatsapp,
        onClick = { state.whatsapp = !state.whatsapp },
        contentPadding = PaddingValues(horizontal = 9.dp, vertical = 7.dp),
        textSize = 14.sp,
        lineHeight = 16.sp
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(Res.drawable.ic_whatsapp_solid),
            tint = Color(0xFF25D366),
            contentDescription = null
        )
    }
    ButtonChoice(
        label = labels.availability.calls,
        color = colors.button,
        selected = state.normalCalls,
        onClick = { state.normalCalls = !state.normalCalls },
        contentPadding = PaddingValues(horizontal = 9.dp, vertical = 7.dp),
        textSize = 14.sp,
        lineHeight = 16.sp
    ) {
        Icon(
            modifier = Modifier.size(16.dp),
            painter = painterResource(Res.drawable.ic_calling_solid),
            tint = Color(0xFF30C0F9),
            contentDescription = null
        )
    }
}