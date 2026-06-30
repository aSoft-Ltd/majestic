package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import majestic.button.appearence.closeModalIconButton
import majestic.button.appearence.constructiveFormButton
import majestic.button.appearence.translucentFormButton
import majestic.button.basic.CloseModalButton
import majestic.button.basic.FormButton
import majestic.format.fromHex
import majestic.format.toCssHex

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPicker(
    title: String,
    label: String,
    color: String?,
    colors: ColorPickerColors,
    onSelect: (String) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) = ColorPicker(
    labels = ColorPickerLabels(
        title = title,
        value = label
    ),
    color = color,
    modifier = modifier,
    colors = colors,
    onSelect = onSelect,
    onDismiss = onDismiss
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorPicker(
    labels: ColorPickerLabels,
    color: String?,
    colors: ColorPickerColors,
    onSelect: (String) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BasicAlertDialog(
        modifier = modifier,
        onDismissRequest = onDismiss
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 324.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .background(colors.body)
            ) {
                var selectedColor by remember(color) { mutableStateOf(color.toPickerHex()) }
                val initialColor = remember(color) { color.toHsvColor() }
                var h by remember(color) { mutableFloatStateOf(initialColor.hue) }
                var s by remember(color) { mutableFloatStateOf(initialColor.saturation) }
                var v by remember(color) { mutableFloatStateOf(initialColor.value) }
                var lastValidColor by remember(color) { mutableStateOf(color.toValidPickerHex()) }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colors.header)
                        .padding(13.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = labels.title,
                        modifier = Modifier.weight(1f),
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp,
                        color = colors.foreground
                    )
                    CloseModalButton(
                        modifier = Modifier.closeModalIconButton(
                            color = colors.foreground,
                            onClick = onDismiss
                        )
                    )
                }
                Column(
                    modifier = Modifier.padding(13.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SVColorCoordinate(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(270.dp)
                            .clip(RoundedCornerShape(6.dp)),
                        hue = h,
                        saturation = s,
                        value = v,
                        cueSize = 9.dp,
                        onChange = { sat, va ->
                            s = sat
                            v = va
                            selectedColor = Color.hsv(h, sat, va).toCssHex().toPickerHex()
                            lastValidColor = selectedColor
                        }
                    )
                    HueBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(percent = 50)),
                        hue = h,
                        cueSize = 5.dp,
                        onChange = {
                            h = it
                            selectedColor = Color.hsv(it, s, v).toCssHex().toPickerHex()
                            lastValidColor = selectedColor
                        }
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = labels.value,
                            color = colors.foreground
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        PickerValue(
                            modifier = Modifier.width(110.dp),
                            pickerColor = selectedColor,
                            onValueChange = { value ->
                                selectedColor = value.toPickerHex()

                                selectedColor.toHsvColorOrNull()?.also {
                                    h = it.hue
                                    s = it.saturation
                                    v = it.value
                                    lastValidColor = selectedColor
                                }
                            },
                            textColor = colors.foreground,
                            backgroundColor = colors.card
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End)
                    ) {
                        FormButton(
                            text = labels.cancel,
                            modifier = Modifier.translucentFormButton(
                                color = colors.foreground,
                                onClick = onDismiss
                            )
                        )
                        FormButton(
                            text = labels.confirm,
                            modifier = Modifier.constructiveFormButton(
                                colors = colors.primaryButton,
                                onClick = { onSelect(lastValidColor) }
                            )
                        )
                    }
                }
            }
        }
    }
}

private data class HsvColor(
    val hue: Float,
    val saturation: Float,
    val value: Float
)

private fun String?.toHsvColor(): HsvColor = runCatching {
    Color.fromHex(this ?: "#000000").toHsvColor()
}.getOrDefault(HsvColor(0f, 0f, 0f))

private fun String.toHsvColorOrNull(): HsvColor? = runCatching {
    Color.fromHex(this).toHsvColor()
}.getOrNull()

private fun Color.toHsvColor(): HsvColor {
    val max = maxOf(red, green, blue)
    val min = minOf(red, green, blue)
    val delta = max - min
    val hue = when {
        delta == 0f -> 0f
        max == red -> 60f * (((green - blue) / delta) % 6f)
        max == green -> 60f * (((blue - red) / delta) + 2f)
        else -> 60f * (((red - green) / delta) + 4f)
    }.let { if (it < 0f) it + 360f else it }

    return HsvColor(
        hue = hue,
        saturation = if (max == 0f) 0f else delta / max,
        value = max
    )
}

private fun String?.toPickerHex(): String = this?.uppercase() ?: "#000000"

private fun String?.toValidPickerHex(): String = runCatching {
    Color.fromHex(this ?: "#000000").toCssHex().toPickerHex()
}.getOrDefault("#000000")
