package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import nation.Country

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DumbCountryDialingCodeSelector(
    value: Country? = null,
    modifier: Modifier = Modifier,
    onClick: ((Country) -> Unit)? = null,
    placeholder: @Composable () -> Unit = { Text("Select") },
    item: @Composable (Country) -> Unit = { CountryDialingCodePreview(it) },
    selected: @Composable (Country) -> Unit = item
) {
    var expanded by remember { mutableStateOf(false) }
    val countries = remember { Country.entries }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = false },
        modifier = modifier
    ) {
        Box(modifier = Modifier
            .exposedDropdownSize()
            .menuAnchor(type = MenuAnchorType.PrimaryEditable)
            .clickable { expanded = !expanded }
            .pointerHoverIcon(PointerIcon.Hand)
        ) {
            when (val candidate = value) {
                null -> placeholder()
                else -> selected(candidate)
            }
        }

        var text by remember { mutableStateOf("") }
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(300.dp)
                .background(color = Color.White)
                .testTag("popup")
        ) {
            DumbSelectSearch(
                modifier = Modifier.height(40.dp),
                text = text,
                elevation = 2.dp,
                backgroundColor = Color.White,
                onChange = { text = it}
            )
            LazyColumn(
                modifier = Modifier.height(200.dp)
                    .width(300.dp)
            ) {
                items(countries) {
                    DropdownMenuItem(
                        modifier = Modifier.fillMaxWidth(),
                        text = { item(it) },
                        onClick = {
                            expanded = false
                            onClick?.invoke(it)
                        },
                    )
                }
            }
        }
    }
}
