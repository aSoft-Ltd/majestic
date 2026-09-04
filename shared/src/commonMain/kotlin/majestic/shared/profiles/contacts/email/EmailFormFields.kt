package majestic.shared.profiles.contacts.email

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.TextField
import majestic.shared.users.label.contacts.DedicatedFormLabels

@Composable
fun EmailFormFields(
    value: String,
    onChange: (String) -> Unit,
    labels: DedicatedFormLabels,
    colors: EmailFormColors,
    modifier: Modifier = Modifier
) = TextField(
        value = value,
        onChange = onChange,
        colors = colors.field,
        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 11.dp),
        label = {
            Text(
                modifier = Modifier.padding(bottom = 8.dp),
                text = labels.input.label,
                color = colors.label,
                fontSize = 13.sp,
                lineHeight = 16.sp
            )
        },
        hint = {
            Text(
                text = labels.input.placeholder,
                color = colors.field.blurred.placeholder,
                fontSize = 14.sp,
                lineHeight = 18.sp
            )
        },
        modifier = modifier
    )