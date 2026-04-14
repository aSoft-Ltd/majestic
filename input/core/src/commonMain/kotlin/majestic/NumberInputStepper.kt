package majestic

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.button.appearence.iconButton
import majestic.button.basic.IconButton
import majestic.icons.Res
import majestic.icons.ic_add_01
import majestic.icons.ic_remove_01
import org.jetbrains.compose.resources.vectorResource
import kotlin.math.pow
import kotlin.math.round

data class NumberInputStepperColors(
    val background: Color,
    val foreground: Color,
    val active: Color,
)

@Composable
fun NumberInputStepper(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    step: Double = 1.0,
    maxDecimals: Int = 0,
    allowNegative: Boolean = true,
    min: Double? = null,
    max: Double? = null,
    colors: NumberInputStepperColors,
    inputWidth: Dp = 50.dp,
) {
    var isFocused by remember { mutableStateOf(false) }

    val effectiveMin = min ?: if (allowNegative) Double.NEGATIVE_INFINITY else 0.0
    val effectiveMax = max ?: Double.POSITIVE_INFINITY

    val pattern = remember(maxDecimals, allowNegative, min) {
        val isNegativeAllowed = allowNegative && (min == null || min < 0.0)
        val negativePart = if (isNegativeAllowed) "-?" else ""
        when {
            maxDecimals == 0 -> Regex("^$negativePart\\d*$")
            maxDecimals == Int.MAX_VALUE -> Regex("^$negativePart\\d*\\.?\\d*$")
            else -> Regex("^$negativePart\\d*\\.?\\d{0,$maxDecimals}$")
        }
    }

    val currentValue = value.toDoubleOrNull() ?: 0.0
    val canDecrement = currentValue > effectiveMin
    val canIncrement = currentValue < effectiveMax

    fun formatMathResult(result: Double): String {
        if (maxDecimals == 0) return result.toLong().toString()

        val roundedResult = if (maxDecimals != Int.MAX_VALUE) {
            val factor = 10.0.pow(maxDecimals.toDouble())
            round(result * factor) / factor
        }
        else {
            result
        }

        val str = roundedResult.toString()
        return if (str.endsWith(".0")) str.dropLast(2) else str
    }

    fun handleTextChange(newText: String) {
        if (newText.isEmpty() || newText.matches(pattern)) {
            val parsed = newText.toDoubleOrNull()
            if (parsed != null && parsed > effectiveMax) return
            onValueChange(newText)
        }
    }

    fun increment() {
        if (canIncrement) {
            val nextValue = minOf(effectiveMax, currentValue + step)
            onValueChange(formatMathResult(nextValue))
        }
    }

    fun decrement() {
        if (canDecrement) {
            val nextValue = maxOf(effectiveMin, currentValue - step)
            onValueChange(formatMathResult(nextValue))
        }
    }

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            icon = vectorResource(Res.drawable.ic_remove_01),
            size = 17.dp,
            alpha = if (canDecrement) 1.0f else 0.3f,
            modifier = Modifier.iconButton(color = colors.foreground, alpha = 0.0f, onClick = { decrement() }),
        )

        Spacer(modifier = Modifier.width(8.dp))

        val borderColor = if (isFocused) colors.active else colors.foreground.copy(alpha = 0.2f)

        BasicTextField(
            value = value,
            onValueChange = { handleTextChange(it) },
            textStyle = LocalTextStyle.current.copy(
                color = colors.foreground.copy(alpha = 0.8f),
                textAlign = TextAlign.Center,
                fontSize = 13.sp
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = if (maxDecimals == 0) KeyboardType.Number else KeyboardType.Decimal
            ),
            singleLine = true,
            cursorBrush = SolidColor(colors.foreground),
            modifier = Modifier
                .width(inputWidth)
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused
                    if (!focusState.isFocused && value.isNotEmpty()) {
                        val parsed = value.toDoubleOrNull() ?: 0.0
                        if (parsed < effectiveMin) onValueChange(formatMathResult(effectiveMin))
                        else if (parsed > effectiveMax) onValueChange(formatMathResult(effectiveMax))
                    }
                }
                .border(
                    width = 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(vertical = 7.dp, horizontal = 5.dp),
            decorationBox = { innerTextField ->
                Box(contentAlignment = Alignment.Center) {
                    if (value.isEmpty()) {
                        Text(
                            text = "0",
                            color = colors.foreground.copy(alpha = 0.3f),
                            fontSize = 13.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                    innerTextField()
                }
            }
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(
            icon = vectorResource(Res.drawable.ic_add_01),
            size = 17.dp,
            alpha = if (canIncrement) 1.0f else 0.3f,
            modifier = Modifier.iconButton(color = colors.foreground, alpha = 0.0f, onClick = { increment() }),
        )
    }
}