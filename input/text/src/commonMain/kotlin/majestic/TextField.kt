package majestic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cinematic.watchAsState
import kollections.firstOrNull
import symphony.BaseField

@Composable
fun TextField(
    field: BaseField<String>,
    modifier: Modifier = Modifier,
    label: String = field.label.capitalizedWithAstrix(),
    hint: String = field.hint,
    color: Color = Color.Black,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onChange: ((String) -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null
) = TextField(
    modifier = modifier,
    field = field,
    hint = hint,
    color = color,
    singleLine = singleLine,
    maxLines = maxLines,
    minLines = minLines,
    keyboardOptions = keyboardOptions,
    visualTransformation = visualTransformation,
    trailingIcon = trailingIcon,
    leadingIcon = leadingIcon,
    onChange = onChange,
    label = {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = label
        )
    }
)

@Composable
fun TextField(
    field: BaseField<String>,
    label: @Composable (() -> Unit),
    hint: String = field.hint,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onChange: ((String) -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    val state = field.state.watchAsState()
    val feedbacks = state.feedbacks.warnings + state.feedbacks.errors
    val hasFeedback = state.feedbacks.warnings.isNotEmpty() || state.feedbacks.errors.isNotEmpty()
    val feedbackColor = when {
        state.feedbacks.errors.isNotEmpty() -> Color.Red
        state.feedbacks.warnings.isNotEmpty() -> Color(0xFF964B00)
        else -> Color(0xFF0061FF)
    }

    Column(modifier = modifier) {
        label()
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().testTag(field.name),
            value = state.output ?: "",
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            placeholder = {
                Text(
                    text = hint,
                    color = color.copy(alpha = 0.4f),
                    fontSize = 17.sp
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = color,
                unfocusedTextColor = color.copy(alpha = 0.7f),
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = feedbackColor,
                unfocusedIndicatorColor = if (hasFeedback) feedbackColor else color.copy(alpha = 0.2f),
            ),
            shape = RoundedCornerShape(8.dp),
            onValueChange = {
                field.set(it)
                onChange?.invoke(it)
            }
        )
        Text(
            color = feedbackColor,
            fontSize = 12.sp,
            text = feedbacks.firstOrNull() ?: ""
        )
    }
}

@Composable
fun TextField(
    value: String,
    label: @Composable (() -> Unit),
    hint: String = "Placeholder",
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onChange: ((String) -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null
) {
    Column(modifier = modifier) {
        label()
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            singleLine = singleLine,
            maxLines = maxLines,
            minLines = minLines,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            placeholder = {
                Text(
                    text = hint,
                    color = color.copy(alpha = 0.4f),
                    fontSize = 17.sp
                )
            },
            colors = TextFieldDefaults.colors(
                focusedTextColor = color,
                unfocusedTextColor = color.copy(alpha = 0.7f),
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color(0xFF0061FF),
                unfocusedIndicatorColor = color.copy(alpha = 0.2f),
            ),
            shape = RoundedCornerShape(8.dp),
            onValueChange = {
                onChange?.invoke(it)
            }
        )
    }
}
