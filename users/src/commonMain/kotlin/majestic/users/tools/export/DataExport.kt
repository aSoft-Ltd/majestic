package majestic.users.tools.export

import org.jetbrains.compose.resources.DrawableResource
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_csv_02
import tz.co.asoft.majestic_users.generated.resources.ic_google_sheet
import tz.co.asoft.majestic_users.generated.resources.ic_pdf_02
import tz.co.asoft.majestic_users.generated.resources.ic_printer

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
