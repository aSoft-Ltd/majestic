package majestic

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DumbSelectSearch(
    text: String = "",
    hint: String = "Search",
    modifier: Modifier = Modifier,
    elevation: Dp = 0.dp,
    background: Color = Color.Unspecified,
    shape: Shape = RoundedCornerShape(8.dp),
    onChange: (String) -> Unit = {},
    onClear: () -> Unit = {},
) = Row(
    modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .shadow(elevation = elevation, shape = shape)
        .background(color = background, shape = shape),
    verticalAlignment = Alignment.CenterVertically,
) {
    BasicTextField(
        modifier = modifier
            .weight(7f)
            .padding(vertical = 10.dp, horizontal = 15.dp),
        value = text,
        onValueChange = {
            println("Basic Text Field on Value Changed called")
            onChange(it)
        },
        decorationBox = { innerTextField ->
            if (text.isEmpty()) {
                Text(
                    text = hint,
                    color = Color.Gray.copy(alpha = 0.5f),
                    style = TextStyle.Default.copy(
                        fontSize = 14.sp
                    )
                )
            }
            println("+Calling innerTextField")
            innerTextField()
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        singleLine = true,
    )
    Box(
        modifier = modifier
            .weight(1f)
            .background(color = Color.Transparent, shape = CircleShape)
            .clickable { onClear() },
    ) {
        if (text.isNotEmpty()) {
            Icon(
                modifier = modifier
                    .fillMaxSize()
                    .padding(5.dp),
                imageVector = Icons.Filled.Close,
                contentDescription = null,
                tint = Color.Black.copy(alpha = 0.7f),
            )
        } else {
            Icon(
                modifier = modifier
                    .fillMaxSize()
                    .padding(5.dp),
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                tint = Color.Gray.copy(alpha = 0.5f),
            )
        }
    }
}