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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import majestic.editor.body.chunks.UnorderedList
import majestic.editor.body.chunksUI.lists.tools.GenericListItem
import majestic.editor.body.chunksUI.lists.tools.ListController
import majestic.editor.body.chunksUI.lists.tools.ListUtilities
import majestic.editor.body.chunksUI.tools.EditorBodyController
import majestic.editor.body.chunksUI.tools.Labels
import majestic.editor.toolbar.EditorColors

@Composable
fun UnorderedChunk(
    list: UnorderedList,
    colors: EditorColors,
    labels: Labels,
    controller: EditorBodyController,
    modifier: Modifier = Modifier
) {
    val bulletType by remember { mutableStateOf(controller.getBulletType(list) ?: list.bulletType) }
    println("UnorderedChunk 1: Bullet type: $bulletType")
    var chunkIndex by remember { mutableStateOf(controller.chunks.indexOfFirst { it.uid == list.uid }) }
    println("UnorderedChunk 2: Chunk index: $chunkIndex")
    val listController = remember { ListController<UnorderedList>() }
    println("UnorderedChunk 3: List controller: $listController")
    LaunchedEffect(controller.chunks) {
        val newIndex = controller.chunks.indexOfFirst { it.uid == list.uid }
        if (newIndex != -1 && newIndex != chunkIndex) chunkIndex = newIndex
    }

    LaunchedEffect(bulletType) {
        list.bulletType = bulletType
        controller.changeBulletType(list, bulletType)
    }

    LaunchedEffect(bulletType, list.items.size) {
        listController.updateAllItems(list, controller) { _ -> ListUtilities.getBulletText(bulletType) }
        println("UnorderedChunk 8: Updated all items with bullet type: $bulletType")
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
                androidx.compose.runtime.key(index) {
                    val bulletText = ListUtilities.getBulletText(bulletType)
                    println("UnorderedChunk: Displaying bulletText: $bulletText")
                    GenericListItem(
                        item = item,
                        index = index,
                        prefixText = bulletText,
                        listController = listController,
                        list = list,
                        controller = controller,
                        colors = colors,
                        createNewItem = ListUtilities::createNewUnorderedItem,
                        mergeWithPrevious = ListUtilities::mergeWithPreviousUnorderedItem
                    )
                    println("UnorderedChunk: Created new item with bulletText: ${ListUtilities::createNewUnorderedItem}")
                    println("UnorderedChunk: Merged with previous item with bulletText: ${ListUtilities::mergeWithPreviousUnorderedItem}")
                    println("UnorderedChunk: GenericListItem: $item")
                }
            }
            SideEffect {
                listController.cleanup(list.items.indices.toSet())
                println("UnorderedChunk 9: Cleaned up list items")
            }
        }
    }
}