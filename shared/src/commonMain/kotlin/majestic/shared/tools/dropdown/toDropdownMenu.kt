package majestic.shared.tools.dropdown

import androidx.compose.runtime.Composable
import majestic.dropdown.DropdownItem
import majestic.shared.tools.menu.OptionMenu
import org.jetbrains.compose.resources.vectorResource

@Composable
fun <T> List<OptionMenu<out T>>.toDropdownItems(): List<DropdownItem<T>> {
    return map { option ->
        DropdownItem(
            value = option.action,
            label = option.label,
            isDestructive = option.isDestructive,
            leadingIcon = if (option.icon !== null) vectorResource(option.icon) else null,
        )
    }
}