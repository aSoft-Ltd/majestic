package majestic.editor.insert

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun InsertHost(
    modifier: Modifier = Modifier.fillMaxSize(),
    controller: InsertHostController,
    editorController: InsertHostController,
) {
    println("Test 3: Selected object is: ${editorController.inserts}")
    val selected = controller.selected

    Column(
        modifier = Modifier.padding(top = 20.dp).fillMaxWidth(.8f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp, alignment = Alignment.Top)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(20.dp, alignment = Alignment.Start),
            verticalAlignment = Alignment.CenterVertically
        ) {
            controller.inserts
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Color(0xFF1D2430), shape = RoundedCornerShape(size = 12.dp))
        ) {
            selected?.content?.let { it() }
        }
    }
}