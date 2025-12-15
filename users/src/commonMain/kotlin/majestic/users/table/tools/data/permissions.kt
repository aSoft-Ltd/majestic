package majestic.users.table.tools.data


import majestic.users.tools.data.Permission
import majestic.users.tools.data.Permissions
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_blog
import tz.co.asoft.majestic_users.generated.resources.ic_calculate
import tz.co.asoft.majestic_users.generated.resources.ic_desk
import tz.co.asoft.majestic_users.generated.resources.ic_invoice
import tz.co.asoft.majestic_users.generated.resources.ic_mail
import tz.co.asoft.majestic_users.generated.resources.ic_megaphone
import tz.co.asoft.majestic_users.generated.resources.ic_message
import tz.co.asoft.majestic_users.generated.resources.ic_notification
import tz.co.asoft.majestic_users.generated.resources.ic_payment_account
import tz.co.asoft.majestic_users.generated.resources.ic_pencil
import tz.co.asoft.majestic_users.generated.resources.ic_refund
import tz.co.asoft.majestic_users.generated.resources.ic_report
import tz.co.asoft.majestic_users.generated.resources.ic_student
import tz.co.asoft.majestic_users.generated.resources.ic_web_preview

internal fun permissions() = buildList {
    this += Permissions(
        resource = Res.drawable.ic_desk,
        title = "Admission",
        description = "Add new applicants, edit applications, enroll students, admit/reject, " +
                "capture admission payments, and manage the full admission lifecycle.",
        permissions = commonPermissions()
    )
    this += Permissions(
        resource = Res.drawable.ic_pencil,
        title = "Academics",
        description = "Add new applicants, edit applications, enroll students, admit/reject, " +
                "capture admission payments, and manage the full admission lifecycle.",
        permissions = commonPermissions()
    )
    this += Permissions(
        resource = Res.drawable.ic_student,
        title = "Students",
        description = "Add new applicants, edit applications, enroll students, admit/reject, " +
                "capture admission payments, and manage the full admission lifecycle.",
        permissions = commonPermissions()
    )

    this += Permissions(
        resource = Res.drawable.ic_notification,
        title = "Notifications",
        description = "Send bulk emails, SMS, and push notifications to students, " +
                "parents, staff, and applicants with templates and tracking.",
        permissions = listOf(
            Permission(
                resource = Res.drawable.ic_mail,
                title = "Email Campaigns",
                description = "Create, schedule, and send bulk emails with open/click tracking.",
                active = true,
                switch = switch
            ),
            Permission(
                resource = Res.drawable.ic_message,
                title = "SMS Broadcast",
                description = "Send instant or scheduled SMS, manage DND, view delivery status.",
                active = true,
                switch = switch
            ),
            Permission(
                resource = Res.drawable.ic_notification,
                title = "Push Notifications",
                description = "Send in-app alerts to mobile app users with targeting and caps.",
                active = true,
                switch = switch
            ),
        )
    )

    this += Permissions(
        resource = Res.drawable.ic_calculate,
        title = "Finance",
        description = "Manage fee structures, generate invoices, process payments, " +
                "handle refunds, and generate financial reports.",
        permissions = listOf(
            Permission(
                resource = Res.drawable.ic_invoice,
                title = "Define Fee Structures",
                description = "Set grade-wise fees, discounts, late fees, and installment plans.",
                active = true,
                switch = switch
            ),
            Permission(
                resource = Res.drawable.ic_invoice,
                title = "Generate & Send Invoices",
                description = "Auto-create invoices, send via email/SMS with payment links.",
                active = true,
                switch = switch
            ),
            Permission(
                resource = Res.drawable.ic_payment_account,
                title = "Record Payments",
                description = "Accept online/offline payments, issue receipts, update ledger.",
                active = true,
                switch = switch
            ),
            Permission(
                resource = Res.drawable.ic_refund,
                title = "Process Refunds",
                description = "Issue refunds, generate receipts, and reconcile accounts.",
                active = true,
                switch = switch
            ),
            Permission(
                resource = Res.drawable.ic_report,
                title = "Financial Reports",
                description = "View P&L, balance sheet, receivables, and cash flow reports.",
                active = true,
                switch = switch
            )
        )
    )

    this += Permissions(
        resource = Res.drawable.ic_web_preview,
        title = "Website",
        description = "Manage public website content, pages, banners, blogs, " +
                "admission forms, and SEO settings.",
        permissions = listOf(
            Permission(
                resource = Res.drawable.ic_invoice,
                title = "Edit Pages",
                description = "Create and update static pages like About, Contact, Gallery.",
                active = true,
                switch = switch
            ),
            Permission(
                resource = Res.drawable.ic_megaphone,
                title = "Manage Banners",
                description = "Upload hero images, set rotation, link to events or forms.",
                active = true,
                switch = switch
            ),
            Permission(
                resource = Res.drawable.ic_blog,
                title = "Blog & News",
                description = "Write, schedule, and publish articles with categories.",
                active = true,
                switch = switch
            ),
            Permission(
                resource = Res.drawable.ic_student,
                title = "Online Admission Form",
                description = "Embed and manage live admission application forms.",
                active = true,
                switch = switch
            )
        )
    )
}
