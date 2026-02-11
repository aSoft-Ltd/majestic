package majestic.choiceSelect.tools

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import composex.screen.orientation.Landscape
import composex.screen.orientation.ScreenOrientation
import majestic.Checkbox
import majestic.CheckboxColors
import majestic.CheckboxMicroColors
import majestic.ColorPair
import majestic.IconCheckCircle
import majestic.IconCheckColors
import majestic.choiceSelect.ChoiceFilterColors
import majestic.choiceSelect.OptionStyle
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SelectOption(
    orientation: ScreenOrientation,
    colors: ChoiceFilterColors,
    name: String,
    icon: DrawableResource,
    isSelected: Boolean,
    style: OptionStyle,
    onToggle: () -> Unit,
    checkboxColors: CheckboxColors?,
    selectedLabelColor: Color?,
    deliverabilityCheckmarkColor: Color?,
    modifier: Modifier
) {
    val muted = colors.default.foreground.copy(0.65f)
    val fallbackSelectedLabel = colors.choice.selected.icon.foreground
    val labelSelected = selectedLabelColor ?: fallbackSelectedLabel
    val textColor = if (isSelected) labelSelected else muted

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        when (style) {

            OptionStyle.TrailingCheckCircle -> {
                Row(
                    modifier = Modifier.weight(1f, fill = true),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(icon),
                        tint = colors.default.foreground.copy(0.7f),
                        contentDescription = null
                    )

                    Text(
                        text = name,
                        fontSize = if(orientation is Landscape)14.sp else 12.sp,
                        color = colors.default.foreground,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(1f, fill = true)
                    )
                }

                val state = if (isSelected) colors.choice.selected else colors.choice.unselected
                IconCheckCircle(
                    size = 16.dp,
                    selected = isSelected,
                    colors = IconCheckColors(
                        background = state.icon.background,
                        border = state.border,
                        icon = state.icon
                    )
                )
            }

            OptionStyle.LeadingCheckbox -> {
                val interaction = remember { MutableInteractionSource() }
                val fallbackState = if (isSelected) colors.choice.selected else colors.choice.unselected
                val fallback = CheckboxColors(
                    selected = CheckboxMicroColors(
                        background = Color.Transparent,
                        border = fallbackState.border,
                        icon = ColorPair(
                            background = fallbackState.icon.background,
                            foreground = fallbackState.icon.foreground
                        )
                    ),
                    unselected = CheckboxMicroColors(
                        background = Color.Transparent,
                        border = colors.default.foreground.copy(0.25f),
                        icon = ColorPair(
                            background = fallbackState.icon.background,
                            foreground = fallbackState.icon.foreground
                        )
                    )
                )

                val cb = checkboxColors ?: fallback
                val micro = if (isSelected) cb.selected else cb.unselected
                Row(
                    modifier = Modifier.weight(1f, fill = true),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        selected = isSelected,
                        colors = micro,
                        modifier = Modifier
                            .size(16.dp)
                            .toggleable(
                                value = isSelected,
                                role = Role.Checkbox,
                                interactionSource = interaction,
                                indication = null,
                                onValueChange = { onToggle() }
                            )
                    )
                    Text(
                        text = name,
                        fontSize = if(orientation is Landscape)14.sp else 12.sp,
                        color = textColor,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 10.dp).weight(1f, fill = true)
                    )
                }
            }

            OptionStyle.TrailingCheck -> {
                Text(
                    text = name,
                    fontSize = 14.sp,
                    color = textColor,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f, fill = true)
                )
                val checkTint = deliverabilityCheckmarkColor ?: labelSelected
                if (isSelected) Icon(
                        modifier = Modifier.size(18.dp),
                        imageVector = Icons.Filled.Check,
                        tint = checkTint,
                        contentDescription = null
                    )
                else Box(modifier = Modifier.size(18.dp))
            }
        }
    }
}