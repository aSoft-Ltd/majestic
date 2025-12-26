package majestic.users.profile.contacts.tools

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.Dark
import majestic.Light
import majestic.ThemeColor

fun isNumber(text: String): Boolean {
    val pattern = Regex("^\\d+\$")
    return text.matches(pattern)
}

@Composable
internal fun OtpInput(
    value: String,
    theme: ThemeColor,
    onValueChange: (String) -> Unit,
    length: Int = 6
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    BasicTextField(
        modifier = Modifier.focusRequester(focusRequester),
        value = value,
        onValueChange = { newValue ->
            val isNumber = newValue.isEmpty() || isNumber(newValue)
            if (newValue.length <= length && isNumber) onValueChange(newValue)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        decorationBox = {
            Row(horizontalArrangement = Arrangement.Center) {
                repeat(length) { index ->
                    val char = when {
                        index >= value.length -> ""
                        else -> value[index].toString()
                    }
                    val isFocused = value.length == index

                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(
                                if (char.isNotEmpty()) theme.dominant.actual.color.copy(alpha = 0.1f)
                                else Color.Transparent
                            )
                            .border(
                                if (isFocused) 2.dp
                                else 1.dp,
                                if (isFocused) when (theme.mode) {
                                    is Dark -> theme.dominant.contra.color
                                    is Light -> theme.dominant.actual.color
                                }
                                else when (theme.mode) {
                                    is Dark -> theme.dominant.contra.color.copy(alpha = 0.2f)
                                    is Light -> theme.dominant.actual.color.copy(alpha = 0.2f)
                                },
                                RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = char, fontSize = 20.sp, color = theme.dominant.contra.color)
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
    )
}
