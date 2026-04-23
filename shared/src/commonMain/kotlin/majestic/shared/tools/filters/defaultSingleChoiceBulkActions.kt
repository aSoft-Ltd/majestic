package majestic.shared.tools.filters

import majestic.icons.Res
import majestic.icons.ic_calendar_01
import majestic.icons.ic_note_edit
import majestic.icons.ic_perfomance
import majestic.icons.ic_school_solid

private val campuses = listOf("Kibaha Campus", "Mwenge Campus", "Zanzibar Campus")
private val curriculums = listOf("NACTE", "Cambridge", "Oxford", "NACTEVET")
private val levels = listOf("All", "O-level", "A-level")
private val dates = listOf("Current", "2025", "2026")

fun defaultSingleChoiceBulkActions(): List<SingleChoiceBulkAction> = listOf(
    SingleChoiceBulkAction(
        icon = Res.drawable.ic_school_solid,
        items = campuses
    ),
    SingleChoiceBulkAction(
        icon = Res.drawable.ic_note_edit,
        items = curriculums
    ),
    SingleChoiceBulkAction(
        icon = Res.drawable.ic_perfomance,
        items = levels
    ),
    SingleChoiceBulkAction(
        icon = Res.drawable.ic_calendar_01,
        items = dates
    )
)