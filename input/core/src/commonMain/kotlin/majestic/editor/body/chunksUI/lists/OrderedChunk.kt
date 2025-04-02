import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.editor.body.chunks.OrderedList
import majestic.editor.body.chunksUI.lists.tools.GenericListItem
import majestic.editor.body.chunksUI.lists.tools.ListController
import majestic.editor.body.chunksUI.lists.tools.ListUtilities
import majestic.editor.body.chunksUI.tools.EditorBodyController
import majestic.editor.body.chunksUI.tools.Labels
import majestic.editor.toolbar.EditorColors

@Composable
fun OrderedChunk(
    list: OrderedList,
    colors: EditorColors,
    labels: Labels,
    controller: EditorBodyController,
    modifier: Modifier = Modifier
) {
    val numberingType by remember { mutableStateOf(controller.getNumberingType(list) ?: list.numberingType) }
    var chunkIndex by remember { mutableStateOf(controller.chunks.indexOfFirst { it.uid == list.uid }) }
    val listController = remember { ListController<OrderedList>() }

    LaunchedEffect(controller.chunks) {
        val newIndex = controller.chunks.indexOfFirst { it.uid == list.uid }
        if (newIndex != -1 && newIndex != chunkIndex) chunkIndex = newIndex
    }

    LaunchedEffect(numberingType) {
        list.numberingType = numberingType
        controller.changeNumberingType(list, numberingType)
    }

    LaunchedEffect(numberingType, list.items.size) {
        listController.updateAllItems(list, controller) { index -> ListUtilities.getNumberText(numberingType, index) }
    }

    Box(
        modifier = modifier
            .background(color = colors.background, shape = RoundedCornerShape(12.dp))
            .padding(horizontal = 10.dp, vertical = 15.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            list.items.forEachIndexed { index, item ->
                key(index) {
                    val numberText = ListUtilities.getNumberText(numberingType, index)
                    GenericListItem(
                        item = item,
                        index = index,
                        prefixText = numberText,
                        listController = listController,
                        list = list,
                        controller = controller,
                        colors = colors,
                        createNewItem = ListUtilities::createNewOrderedItem,
                        mergeWithPrevious = ListUtilities::mergeWithPreviousOrderedItem,
                        label = labels
                    )
                }
            }
            SideEffect {
                listController.cleanup(list.items.indices.toSet())
            }
        }
    }
}