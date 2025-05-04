package majestic.drawer

import androidx.compose.ui.unit.Dp

interface SingleDrawerController {
    fun open()
    fun open(ratio: Float)
    fun open(span: Dp)

    fun close()

    fun toggle()
    fun toggle(ratio: Float)
    fun toggle(span: Dp)
}