package academia.users.labels.profile.data

import majestic.users.labels.profile.data.switch
import majestic.users.tools.data.Permission
import tz.co.asoft.majestic_users.generated.resources.Res
import tz.co.asoft.majestic_users.generated.resources.ic_edit_user
import tz.co.asoft.majestic_users.generated.resources.ic_reminder
import tz.co.asoft.majestic_users.generated.resources.ic_remove
import tz.co.asoft.majestic_users.generated.resources.ic_student

val switch = "On" to "Off"
fun commonPermissions(): List<Permission> = listOf(
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
        resource = Res.drawable.ic_remove,
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