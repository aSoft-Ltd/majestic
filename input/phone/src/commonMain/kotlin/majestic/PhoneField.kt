package majestic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cinematic.watchAsState
import kollections.first
import kollections.isNotEmpty
import kollections.plus
import nation.Country
import symphony.PhoneField

@Composable
fun PhoneField(
    field: PhoneField,
    label: @Composable (() -> Unit),
    hint: String = field.hint,
    country: @Composable (Country) -> Unit = {
        CountryDialingCodePreview(it, modifier = Modifier.padding(10.dp).testTag(it.code))
    },
    colors: PhoneFieldColors = PhoneFieldColors(),
    selected: @Composable (Country) -> Unit = country,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onChange: ((String) -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = {
        CountryDialingCodeSelector(
            field = field,
            modifier = Modifier.width(110.dp).testTag("${field.name}-selector"),
            selected = selected,
            item = country
        )
    }
) {
    val state = field.state.watchAsState()
    val feedbacks = state.feedbacks.warnings + state.feedbacks.errors
    val hasFeedback = state.feedbacks.warnings.isNotEmpty() || state.feedbacks.errors.isNotEmpty()
    val color = when {
        state.feedbacks.errors.isNotEmpty() -> Color.Red
        state.feedbacks.warnings.isNotEmpty() -> Color(0xFF964B00)
        else -> Color(0xFF0061FF)
    }

    Column(modifier = modifier) {
        label()
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().testTag(field.name),
            singleLine = true,
            value = state.output?.body?.toString() ?: "",
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            placeholder = {
                Text(
                    text = hint,
                    color = colors.blurred.placeholder,
                    fontSize = 17.sp
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = colors.focused.text,
                unfocusedTextColor = colors.blurred.text,
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = colors.focused.border,
                unfocusedIndicatorColor = if (hasFeedback) color else colors.blurred.border,
            ),
            shape = RoundedCornerShape(8.dp),
            onValueChange = {
                field.setBody(it)
                onChange?.invoke(it)
            }
        )
        if (hasFeedback) Text(
            color = color,
            fontSize = 12.sp,
            text = feedbacks.first()
        )
    }
}

class PhoneFieldMicroColors(
    val border: Color,
    val placeholder: Color,
    val text: Color
)

class PhoneFieldColors(
    val focused: PhoneFieldMicroColors = PhoneFieldMicroColors(
        border = Color(0xFF0061FF),
        placeholder = Color.Black.copy(alpha = 0.4f),
        text = Color.Black
    ),
    val blurred: PhoneFieldMicroColors = PhoneFieldMicroColors(
        border = Color.Black.copy(alpha = 0.2f),
        placeholder = Color.Black.copy(alpha = 0.4f),
        text = Color.Black
    ),
    val error: PhoneFieldMicroColors = PhoneFieldMicroColors(
        border = Color.Red.copy(alpha = 0.8f),
        placeholder = Color.Red.copy(alpha = 0.8f),
        text = Color.Red.copy(0.8f)
    )
)
