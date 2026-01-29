package majestic.shared.dataexport

import org.jetbrains.compose.resources.DrawableResource
import majestic.icons.Res
import majestic.icons.ic_csv_02
import majestic.icons.ic_google_sheet
import majestic.icons.ic_pdf_02
import majestic.icons.ic_printer

enum class DataExport {
    PRINT, PDF, EXCEL, CSV;

    val icon: DrawableResource
        get() = when (this) {
            PDF -> Res.drawable.ic_pdf_02
            CSV -> Res.drawable.ic_csv_02
            EXCEL -> Res.drawable.ic_google_sheet
            PRINT -> Res.drawable.ic_printer
        }

    val label: String
        get() = when (this) {
            PRINT -> "Print"
            PDF -> "PDF"
            EXCEL -> "Excel"
            CSV -> "CSV"
        }
}