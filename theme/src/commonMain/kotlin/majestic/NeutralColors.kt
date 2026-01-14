package majestic

import androidx.compose.ui.graphics.Color

internal object NeutralColors {
    val white by lazy { ContraColor.light(Color(0xFFFFFFFF)) }
    val black by lazy { ContraColor.dark(Color(0xFF121212)) }
}