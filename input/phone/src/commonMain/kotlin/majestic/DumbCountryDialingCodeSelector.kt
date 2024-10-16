package majestic

import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import nation.Country

@Composable
fun DumbCountryDialingCodeSelector(
    value: Country? = null,
    countries: List<Country> = Country.entries,
    modifier: Modifier = Modifier,
    onClick: ((Country) -> Unit)? = null,
    placeholder: @Composable () -> Unit = { Text("Select") },
    onSearch: (String) -> Unit = {},
    item: @Composable (Country) -> Unit = { CountryDialingCodePreview(it) },
    selected: @Composable (Country) -> Unit = item
) {
    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf("") }
    val interaction = remember { NoRippleInteractionSource() }

    Box(modifier = Modifier
        .testTag("CountrySelector")
        .clickable(
            interactionSource = interaction,
            indication = LocalIndication.current
        ) { expanded = !expanded }
        .pointerHoverIcon(PointerIcon.Hand)
    ) {
        when (value) {
            null -> placeholder()
            else -> selected(value)
        }
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier
            .width(350.dp)
            .background(color = Color.White)
            .testTag("CountrySelectorPopup"),
    ) {
        val focus = remember { FocusRequester() }

        LaunchedEffect(focus, expanded) {
            delay(3000)
            if (expanded) focus.requestFocus()
        }

        DumbSelectSearch(
            modifier = Modifier
                .focusRequester(focus)
                .height(40.dp),
            text = text,
            elevation = 2.dp,
            background = Color.White,
            onChange = {
                text = it
                onSearch(it)
            },
            onClear = {
                text = ""
                onSearch(text)
            }
        )
        LazyColumn(
            modifier = Modifier
                .height(200.dp)
                .width(350.dp)
                .testTag("CountrySelectorList")
        ) {
            items(countries) { country ->
                DropdownMenuItem(
                    modifier = Modifier.fillMaxWidth().testTag("${country.dialingCode}"),
                    text = { item(country) },
                    onClick = {
                        expanded = false
                        onClick?.invoke(country)
                    },
                )
            }
        }
    }
}
