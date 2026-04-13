package majestic.shared.notices.labels

import majestic.shared.notices.list.noticecard.NoticeCardLabels

data class NoticeLabels(
    val bar: TopBarLabels,
    val empty: EmptyNoticeLabels,
    val card: NoticeCardLabels,
    val time: NoticeTimeLabels
)
