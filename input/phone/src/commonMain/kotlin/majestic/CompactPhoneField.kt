package majestic

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cinematic.watchAsState
import symphony.PhoneField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompactPhoneField(
    field: PhoneField,
    label: @Composable (() -> Unit),
    hint: String = field.hint,
    colors: PhoneFieldColors = PhoneFieldColors(),
    contentPadding: PaddingValues = PaddingValues(16.dp),
    textSize: TextUnit = 17.sp,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onChange: ((String) -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val state = field.state.watchAsState()
    val value = state.output?.body?.toString() ?: ""
    val feedbacks = state.feedbacks.warnings + state.feedbacks.errors
    val hasFeedback = state.feedbacks.warnings.isNotEmpty() || state.feedbacks.errors.isNotEmpty()
    val feedbackColor = when {
        state.feedbacks.errors.isNotEmpty() -> Color.Red
        state.feedbacks.warnings.isNotEmpty() -> Color(0xFF964B00)
        else -> Color(0xFF0061FF)
    }
    val interactionSource = remember { MutableInteractionSource() }
    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = colors.focused.text,
        unfocusedTextColor = colors.blurred.text,
        focusedContainerColor = Color.Transparent,
        unfocusedContainerColor = Color.Transparent,
        focusedBorderColor = colors.focused.border,
        unfocusedBorderColor = if (hasFeedback) feedbackColor else colors.blurred.border,
        cursorColor = colors.focused.text
    )

    Column(modifier = modifier) {
        label()
        BasicTextField(
            value = value,
            onValueChange = {
                field.setBody(it)
                onChange?.invoke(it)
            },
            singleLine = true,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            textStyle = TextStyle(color = colors.focused.text, fontSize = textSize),
            cursorBrush = SolidColor(colors.focused.text),
            interactionSource = interactionSource,
            modifier = Modifier.fillMaxWidth().testTag(field.name)
        ) { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = true,
                singleLine = true,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                placeholder = {
                    Text(
                        text = hint,
                        color = colors.blurred.placeholder,
                        fontSize = textSize
                    )
                },
                leadingIcon = leadingIcon,
                trailingIcon = trailingIcon,
                colors = fieldColors,
                contentPadding = contentPadding,
                container = {
                    OutlinedTextFieldDefaults.Container(
                        enabled = true,
                        isError = state.feedbacks.errors.isNotEmpty(),
                        interactionSource = interactionSource,
                        colors = fieldColors,
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            )
        }
        if (hasFeedback) Text(
            text = feedbacks.first(),
            color = feedbackColor,
            fontSize = 12.sp
        )
    }
}