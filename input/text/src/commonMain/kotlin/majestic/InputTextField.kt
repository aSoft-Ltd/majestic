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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cinematic.watchAsState
import kollections.firstOrNull
import neat.Valid
import neat.Validity
import symphony.BaseField
import symphony.toErrors

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    field: BaseField<String>,
    label: String = field.label.capitalizedWithAstrix(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    onChange: ((String) -> Unit)? = null
) {
    val colorBlue = Color(red = 0x00, green = 0x61, blue = 0xFF)
    val state = field.state.watchAsState()
    var validity: Validity<Any?> by remember(state.output) { mutableStateOf(Valid(state.output)) }

    LaunchedEffect(state.output) {
        validity = field.validate()
    }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = label
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            value = state.output ?: "",
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            placeholder = {
                Text(
                    text = field.hint,
                    color = Color.Black.copy(alpha = 0.4f),
                    fontSize = 17.sp
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = if (validity is Valid) colorBlue else Color.Red,
                unfocusedIndicatorColor = Color.Black.copy(alpha = 0.2f),
            ),
            shape = RoundedCornerShape(8.dp),
            onValueChange = {
                field.set(it)
                onChange?.invoke(it)
            }
        )
        Text(
            color = Color.Red,
            fontSize = 12.sp,
            text = validity.toErrors().firstOrNull()?.message ?: ""
        )
    }
}
