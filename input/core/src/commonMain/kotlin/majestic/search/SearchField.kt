package majestic.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
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
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import majestic.Dark
import majestic.Light
import majestic.ThemeColor
import majestic.tools.base
import majestic.tools.withOverlay

private val ThemeColor.searchBackgroundColor: Color
    get() {
        val alpha = when (this) {
            is Light -> 0.25f
            is Dark -> 0.15f
        }
        return dominant.actual.color.copy(alpha = alpha).compositeOver(surface.actual.color)
    }

data class SearchColors(
    val background: Color,
    val text: Color,
    val hint: Color,
    val border: Color,
    val iconBg: Color,
    val iconTint: Color
)

fun ThemeColor.toSearchColors(): SearchColors {
    val baseColor = surface.contra.color
    return when (this) {
        is Dark -> SearchColors(
            background = base.withOverlay(surface.contra.color, 0.2f),
            text = baseColor.copy(alpha = 0.8f),
            hint = baseColor.copy(alpha = 0.4f),
            border = baseColor.copy(alpha = 0.1f),
            iconBg = base.withOverlay(baseColor, 0.5f),
            iconTint = baseColor
        )

        is Light -> SearchColors(
            background = base.withOverlay(surface.contra.color, 0.2f),
            text = baseColor.copy(alpha = 0.8f),
            hint = baseColor.copy(alpha = 0.4f),
            border = baseColor.copy(alpha = 0.12f),
            iconBg = base.withOverlay(baseColor, 0.5f),
            iconTint = baseColor
        )
    }
}

@Deprecated(
    message = "This implementation is deprecated",
    replaceWith = ReplaceWith("majestic.Search")
)
@Composable
fun SearchField(
    theme: ThemeColor,
    modifier: Modifier,
    hint: String = "Search",
    value: String?,
    onChange: (String) -> Unit,
    icon: @Composable () -> Unit
) = Row(
    modifier = modifier,
    verticalAlignment = Alignment.CenterVertically
) {
    var text by remember { mutableStateOf(value.orEmpty()) }
    var showHint by remember { mutableStateOf(false) }

    BasicTextField(
        value = text,
        onValueChange = { new ->
            text = new
            onChange(new)
        },
        modifier = Modifier.weight(1f).onFocusChanged { showHint = it.isFocused },
        singleLine = true,
        cursorBrush = SolidColor(theme.toSearchColors().text),
        textStyle = TextStyle(color = theme.toSearchColors().text, fontSize = 14.sp),
        decorationBox = { inner ->
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ) {
                if (!showHint && text.isEmpty()) Text(
                    text = hint,
                    color = theme.toSearchColors().hint,
                    fontSize = 14.sp
                ) else inner()
            }
        }
    )
    icon()
}