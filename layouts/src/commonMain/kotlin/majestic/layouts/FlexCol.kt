package majestic.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

class FlexCol(
    val arrangement: Arrangement.Vertical = Arrangement.spacedBy(20.dp),
    val alignment: Alignment.Horizontal = Alignment.Start
)