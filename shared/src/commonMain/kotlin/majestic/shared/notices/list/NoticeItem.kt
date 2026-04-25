package majestic.shared.notices.list

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

data class NoticeItem(
    val title: String,
    val info: List<String>,
    val targets: List<String>,
    val time: String,
    val description: String,
    val avatars: List<Painter>,
    val progress: String,
    val status: NoticeStatus,
    val hasAlert: Boolean = false
)
