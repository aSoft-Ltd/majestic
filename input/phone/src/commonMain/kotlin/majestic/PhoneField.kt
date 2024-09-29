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
import kollections.firstOrNull
import kollections.isNotEmpty
import kollections.plus
import symphony.PhoneField

@Composable
fun PhoneField(
    field: PhoneField,
    label: @Composable (() -> Unit),
    hint: String = field.hint,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onChange: ((String) -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = {
        CountryDialingCodeSelector(
            field = field,
            modifier = Modifier.width(110.dp).testTag("${field.name}-selector"),
            selected = {
                CountryDialingCodePreview(it, modifier = Modifier.padding(10.dp).testTag(it.code))
            }
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
                    color = Color.Black.copy(alpha = 0.4f),
                    fontSize = 17.sp
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = color,
                unfocusedIndicatorColor = if (hasFeedback) color else Color.Black.copy(alpha = 0.2f),
            ),
            shape = RoundedCornerShape(8.dp),
            onValueChange = {
                field.setBody(it)
                onChange?.invoke(it)
            }
        )
        Text(
            color = color,
            fontSize = 12.sp,
            text = feedbacks.firstOrNull() ?: ""
        )
    }
}