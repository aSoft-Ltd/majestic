package majestic.shared.notices.list.noticecard

import majestic.shared.notices.list.NoticeStatus

data class NoticeStatusLabels(
    val posting: String,
    val posted: String,
    val failed: String,
    val draft: String
)

data class NoticeCardLabels(
    val target: String,
    val view: String,
    val cancel: String,
    val delete: String,
    val status: NoticeStatusLabels
)

fun NoticeStatusLabels.get(status: NoticeStatus) = when (status) {
    NoticeStatus.Posting -> posting
    NoticeStatus.Posted -> posted
    NoticeStatus.Failed -> failed
    NoticeStatus.Draft -> draft
}
