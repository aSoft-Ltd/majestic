package majestic.shared.profiles.contacts.phone.form

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.CountryDialingCodeSelector
import majestic.NoRippleInteractionSource
import majestic.CompactPhoneField
import majestic.shared.profiles.contacts.phone.PhoneFormColors
import majestic.shared.tools.DialingCodePreview
import majestic.shared.users.label.contacts.DedicatedFormLabels
import symphony.PhoneField

@Composable
internal fun Form(
    field: PhoneField,
    labels: DedicatedFormLabels,
    colors: PhoneFormColors,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    textSize: TextUnit = 17.sp,
    modifier: Modifier = Modifier
) {
    CompactPhoneField(
        field = field,
        modifier = modifier,
        hint = labels.input.placeholder,
        colors = colors.phoneField,
        contentPadding = contentPadding,
        textSize = textSize,
        label = {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = labels.input.label,
                color = colors.label,
                fontSize = 13.sp,
                lineHeight = 16.sp
            )
        },
        leadingIcon = {
            var expanded by remember { mutableStateOf(false) }
            val interaction = remember { NoRippleInteractionSource }
            CountryDialingCodeSelector(
                modifier = Modifier
                    .testTag("CountrySelector")
                    .clickable(
                        interactionSource = interaction,
                        indication = LocalIndication.current
                    ) { expanded = !expanded }
                    .pointerHoverIcon(PointerIcon.Hand)
                    .width(100.dp),
                field = field,
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
}