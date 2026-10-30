package majestic.shared.tools.filters

import majestic.icons.Res
import majestic.icons.ic_calendar_01
import majestic.icons.ic_note_edit
import majestic.icons.ic_perfomance
import majestic.icons.ic_school_solid

const val NECTA_CURRICULUM = "NECTA"
const val NACTEVET_CURRICULUM = "NACTEVET"
const val ALL_LEVELS = "All"
const val ORDINARY_LEVEL = "O-level"
const val ADVANCED_LEVEL = "A-level"
const val CERTIFICATE_LEVEL = "Certificate"
const val DIPLOMA_LEVEL = "Diploma"

private val campuses = listOf("Kibaha Campus", "Mwenge Campus", "Zanzibar Campus")
private val curriculums = listOf(NECTA_CURRICULUM, "Cambridge", "Oxford", NACTEVET_CURRICULUM)
private val dates = listOf("Current", "2025", "2026")

fun levelsOf(curriculum: String): List<String> =
    if (curriculum == NACTEVET_CURRICULUM)
        listOf(ALL_LEVELS, CERTIFICATE_LEVEL, DIPLOMA_LEVEL)
    else
        listOf(ALL_LEVELS, ORDINARY_LEVEL, ADVANCED_LEVEL)

fun defaultSingleChoiceBulkActions(
    onCurriculumSelection: (String) -> Unit = {},
    onLevelSelection: (String) -> Unit = {},
    curriculum: String = NECTA_CURRICULUM,
    level: String = ALL_LEVELS
): List<SingleChoiceBulkAction> {
    val levels = levelsOf(curriculum)
    return listOf(
        SingleChoiceBulkAction(
            icon = Res.drawable.ic_school_solid,
            items = campuses
        ),
        SingleChoiceBulkAction(
            icon = Res.drawable.ic_note_edit,
            items = curriculums,
            defaultSelection = curriculum,
            onSelection = onCurriculumSelection
        ),
        SingleChoiceBulkAction(
            icon = Res.drawable.ic_perfomance,
            items = levels,
            defaultSelection = level.takeIf { it in levels } ?: levels.first(),
            onSelection = onLevelSelection
        ),
        SingleChoiceBulkAction(
            icon = Res.drawable.ic_calendar_01,
            items = dates
        )
    )
}