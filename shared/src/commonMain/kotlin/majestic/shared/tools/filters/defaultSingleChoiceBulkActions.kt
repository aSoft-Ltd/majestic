package majestic.shared.tools.filters

import majestic.icons.Res
import majestic.icons.ic_calendar_01
import majestic.icons.ic_note_edit
import majestic.icons.ic_perfomance
import majestic.icons.ic_school_solid

const val ALL_LEVELS = "All"
const val ORDINARY_LEVEL = "O-level"
const val ADVANCED_LEVEL = "A-level"
const val NACTEVET_CURRICULUM = "NACTEVET"

private val campuses = listOf("Kibaha Campus", "Mwenge Campus", "Zanzibar Campus")
private val curriculums = listOf("NACTE", "Cambridge", "Oxford", NACTEVET_CURRICULUM)
private val levels = listOf(ALL_LEVELS, ORDINARY_LEVEL, ADVANCED_LEVEL)
private val dates = listOf("Current", "2025", "2026")

fun defaultSingleChoiceBulkActions(
    onCurriculumSelection: (String) -> Unit = {},
    onLevelSelection: (String) -> Unit = {},
    curriculum: String? = null,
    level: String = ALL_LEVELS
): List<SingleChoiceBulkAction> = listOf(
    SingleChoiceBulkAction(
        icon = Res.drawable.ic_school_solid,
        items = campuses
    ),
    SingleChoiceBulkAction(
        icon = Res.drawable.ic_note_edit,
        items = curriculums,
        defaultSelection = curriculum?.takeIf { it in curriculums },
        onSelection = onCurriculumSelection
    ),
    SingleChoiceBulkAction(
        icon = Res.drawable.ic_perfomance,
        items = levels,
        defaultSelection = level,
        onSelection = onLevelSelection
    ),
    SingleChoiceBulkAction(
        icon = Res.drawable.ic_calendar_01,
        items = dates
    )
)