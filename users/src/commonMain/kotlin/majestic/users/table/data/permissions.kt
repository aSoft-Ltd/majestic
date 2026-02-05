package majestic.users.table.data

import majestic.icons.Res
import majestic.icons.ic_blog
import majestic.icons.ic_calculate
import majestic.icons.ic_desk
import majestic.icons.ic_invoice
import majestic.icons.ic_mail
import majestic.icons.ic_megaphone
import majestic.icons.ic_message
import majestic.icons.ic_notification
import majestic.icons.ic_payment_account
import majestic.icons.ic_pencil
import majestic.icons.ic_refund
import majestic.icons.ic_report
import majestic.icons.ic_student
import majestic.icons.ic_web_preview
import majestic.shared.profiles.Action
import majestic.shared.profiles.Permission

fun permissions() = buildList {
    this += Permission(
        resource = Res.drawable.ic_desk,
        title = "Admission",
        description = "Add new applicants, edit applications, enroll students, admit/reject, " +
                "capture admission payments, and manage the full admission lifecycle.",
        actions = commonPermissions()
    )
    this += Permission(
        resource = Res.drawable.ic_pencil,
        title = "Academics",
        description = "Add new applicants, edit applications, enroll students, admit/reject, " +
                "capture admission payments, and manage the full admission lifecycle.",
        actions = commonPermissions()
    )
    this += Permission(
        resource = Res.drawable.ic_student,
        title = "Students",
        description = "Add new applicants, edit applications, enroll students, admit/reject, " +
                "capture admission payments, and manage the full admission lifecycle.",
        actions = commonPermissions()
    )

    this += Permission(
        resource = Res.drawable.ic_notification,
        title = "Notifications",
        description = "Send bulk emails, SMS, and push notifications to students, " +
                "parents, staff, and applicants with templates and tracking.",
        actions = listOf(
            Action(
                resource = Res.drawable.ic_mail,
                title = "Email Campaigns",
                description = "Create, schedule, and send bulk emails with open/click tracking.",
                active = true,
                switch = switch
            ),
            Action(
                resource = Res.drawable.ic_message,
                title = "SMS Broadcast",
                description = "Send instant or scheduled SMS, manage DND, view delivery status.",
                active = true,
                switch = switch
            ),
            Action(
                resource = Res.drawable.ic_notification,
                title = "Push Notifications",
                description = "Send in-app alerts to mobile app users with targeting and caps.",
                active = true,
                switch = switch
            ),
        )
    )

    this += Permission(
        resource = Res.drawable.ic_calculate,
        title = "Finance",
        description = "Manage fee structures, generate invoices, process payments, " +
                "handle refunds, and generate financial reports.",
        actions = listOf(
            Action(
                resource = Res.drawable.ic_invoice,
                title = "Define Fee Structures",
                description = "Set grade-wise fees, discounts, late fees, and installment plans.",
                active = true,
                switch = switch
            ),
            Action(
                resource = Res.drawable.ic_invoice,
                title = "Generate & Send Invoices",
                description = "Auto-create invoices, send via email/SMS with payment links.",
                active = true,
                switch = switch
            ),
            Action(
                resource = Res.drawable.ic_payment_account,
                title = "Record Payments",
                description = "Accept online/offline payments, issue receipts, update ledger.",
                active = true,
                switch = switch
            ),
            Action(
                resource = Res.drawable.ic_refund,
                title = "Process Refunds",
                description = "Issue refunds, generate receipts, and reconcile accounts.",
                active = true,
                switch = switch
            ),
            Action(
                resource = Res.drawable.ic_report,
                title = "Financial Reports",
                description = "View P&L, balance sheet, receivables, and cash flow reports.",
                active = true,
                switch = switch
            )
        )
    )

    this += Permission(
        resource = Res.drawable.ic_web_preview,
        title = "Website",
        description = "Manage public website content, pages, banners, blogs, " +
                "admission forms, and SEO settings.",
        actions = listOf(
            Action(
                resource = Res.drawable.ic_invoice,
                title = "Edit Pages",
                description = "Create and update static pages like About, Contact, Gallery.",
                active = true,
                switch = switch
            ),
            Action(
                resource = Res.drawable.ic_megaphone,
                title = "Manage Banners",
                description = "Upload hero images, set rotation, link to events or forms.",
                active = true,
                switch = switch
            ),
            Action(
                resource = Res.drawable.ic_blog,
                title = "Blog & News",
                description = "Write, schedule, and publish articles with categories.",
                active = true,
                switch = switch
            ),
            Action(
                resource = Res.drawable.ic_student,
                title = "Online Admission Form",
                description = "Embed and manage live admission application forms.",
                active = true,
                switch = switch
            )
        )
    )
}
