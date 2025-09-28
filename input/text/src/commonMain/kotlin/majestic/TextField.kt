package majestic

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.remember
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.TextUnit
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
    colors: TextFieldColors = TextFieldColors(),
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onChange: ((String) -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    hintSize: TextUnit = 17.sp
) = TextField(
    modifier = modifier,
    field = field,
    hint = hint,
    color = color,
    colors = colors,
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
    },
    shape = shape,
    hintSize = hintSize
)


// Why do we have two different implementations
@Composable
fun TextField(
    field: BaseField<String>,
    label: @Composable (() -> Unit),
    hint: String = field.hint,
    modifier: Modifier = Modifier,
    color: Color = Color.Black,
    colors: TextFieldColors = TextFieldColors(),
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onChange: ((String) -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    hintSize: TextUnit = 17.sp
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
                    fontSize = hintSize
                )
            },
            colors = colors.toMaterialTextFieldColors(),
            shape = shape,
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

class TextFieldMicroColors(
    val border: Color,
    val placeholder: Color,
    val text: Color
)

class TextFieldColors(
    val focused: TextFieldMicroColors = TextFieldMicroColors(
        border = Color(0xFF0061FF),
        placeholder = Color.Black.copy(alpha = 0.4f),
        text = Color.Black
    ),
    val blurred: TextFieldMicroColors = TextFieldMicroColors(
        border = Color.Black.copy(alpha = 0.2f),
        placeholder = Color.Black.copy(alpha = 0.4f),
        text = Color.Black
    ),
    val error: TextFieldMicroColors = TextFieldMicroColors(
        border = Color.Red.copy(alpha = 0.8f),
        placeholder = Color.Red.copy(alpha = 0.8f),
        text = Color.Red.copy(0.8f)
    )
)

/**
 * This TextField uses [BasicTextField] internally to allow user to truly control its height and width
 * with the padding values passed inside its [contentPadding] parameter.
 *
 * It is meant to overcome the limitation of TextFields using material implementations which have a fixed
 * minHeight(56.dp) following material guidelines, which is inconvenient especially in KMP projects
 * @param contentPadding pass [PaddingValues] with vertical or horizontal to set space between the content and
 * the TextField's border, to control size of the TextField.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextField(
    value: String,
    onChange: ((String) -> Unit)? = null,
    label: @Composable (() -> Unit)? = null,
    colors: TextFieldColors = TextFieldColors(),
    hintSize: TextUnit = 17.sp,
    hint: @Composable () -> Unit = {
        Text(
            text = "Placeholder",
            color = colors.blurred.placeholder,
            fontSize = hintSize
        )
    },
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(vertical = 14.dp, horizontal = 14.dp),
    readOnly: Boolean = false,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    enabled: Boolean = true,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    shape: Shape = RoundedCornerShape(8.dp),
    textStyle: TextStyle = TextStyle.Default.copy(color = colors.focused.text),
    interactionSource: MutableInteractionSource? = null,
    isError: Boolean = false,
) {
    @Suppress("NAME_SHADOWING")
    val interactionSource = interactionSource ?: remember { MutableInteractionSource() }
    val materialColors = colors.toMaterialTextFieldOutlinedColors()

    Column(modifier = modifier) {
        if (label != null) {
            label()
        }
        BasicTextField(
            value = value,
            onValueChange = {
                onChange?.invoke(it)
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = visualTransformation,
            interactionSource = interactionSource,
            enabled = enabled,
            singleLine = singleLine,
            textStyle = textStyle,
            readOnly = readOnly,
            cursorBrush = SolidColor(materialColors.focusedTextColor),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = maxLines,
            minLines = minLines,
        ) { innerTextField ->
            // places leading icon, text field with label and placeholder, trailing icon
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                visualTransformation = visualTransformation,
                innerTextField = innerTextField,
                singleLine = singleLine,
                enabled = enabled,
                interactionSource = interactionSource,
                contentPadding = contentPadding,
                trailingIcon = trailingIcon,
                leadingIcon = leadingIcon,
                placeholder = hint,
                colors = materialColors,
                container = {
                    OutlinedTextFieldDefaults.Container(
                        enabled = enabled,
                        isError = isError,
                        interactionSource = interactionSource,
                        colors = materialColors,
                        shape = shape,
                    )
                }
            )
        }
    }
}


@Composable
fun TextField(
    value: String,
    label: @Composable (() -> Unit)? = null,
    hint: String = "Placeholder",
    modifier: Modifier = Modifier,
    colors: TextFieldColors = TextFieldColors(),
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onChange: ((String) -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    shape: RoundedCornerShape = RoundedCornerShape(8.dp),
    textStyle: TextStyle = TextStyle.Default.copy(color = colors.focused.text),
    hintSize: TextUnit = 17.sp
) {
    TextField(
        value = value,
        label = label,
        hint = {
            Text(
                text = hint,
                color = colors.blurred.placeholder,
                fontSize = hintSize
            )
        },
        modifier = modifier,
        colors = colors,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        onChange = onChange,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        shape = shape,
        readOnly = readOnly,
        textStyle = textStyle,
        hintSize = hintSize
    )
}

@Composable
private fun TextFieldColors.toMaterialTextFieldColors() = TextFieldDefaults.colors(
    focusedTextColor = focused.text,
    unfocusedTextColor = blurred.text,
    focusedContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent,
    focusedIndicatorColor = focused.border,
    unfocusedIndicatorColor = blurred.border,
)


// TODO: Seek out other arguments of OutlinedTextDefaults.colors to fill up
@Composable
private fun TextFieldColors.toMaterialTextFieldOutlinedColors() = OutlinedTextFieldDefaults.colors(
    focusedTextColor = focused.text,
    unfocusedTextColor = blurred.text,
    cursorColor = focused.text,
    focusedContainerColor = Color.Transparent,
    unfocusedContainerColor = Color.Transparent,
    focusedBorderColor = focused.border,
    unfocusedBorderColor = blurred.border,
)