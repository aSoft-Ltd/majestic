package majestic.users.table.tools.data

import profiles.Permission
import majestic.icons.Res
import majestic.icons.ic_edit_user
import majestic.icons.ic_reminder
import majestic.icons.ic_student
import majestic.icons.ic_user_remove

val switch = "On" to "Off"
internal fun commonPermissions(): List<Permission> = listOf(
    Permission(
        resource = Res.drawable.ic_student,
        title = "Enroll Students",
        description = "Complete registration, verify details, and confirm enrollment. " +
                "Sends confirmation via email/SMS.",
        active = true,
        switch = switch
    ),
    Permission(
        resource = Res.drawable.ic_reminder,
        title = "Send Reminders",
        description = "Send follow-ups, resend emails/SMS, admission confirmations, " +
                "and rejection notices.",
        active = true,
        switch = switch
    ),
    Permission(
        resource = Res.drawable.ic_user_remove,
        title = "Reject Applicants",
        description = "Reject unqualified applicants, revoke rejections, " +
                "and record rejection reasons.",
        active = true,
        switch = switch
    ),
    Permission(
        resource = Res.drawable.ic_edit_user,
        title = "Admit Applicants",
        description = "Admit students, re-admit, process pending applications, " +
                "and view admission history.",
        active = true,
        switch = switch
    )
)