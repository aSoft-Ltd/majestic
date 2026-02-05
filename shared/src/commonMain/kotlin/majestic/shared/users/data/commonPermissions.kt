package majestic.shared.users.data

import majestic.icons.Res
import majestic.icons.ic_edit_user
import majestic.icons.ic_reminder
import majestic.icons.ic_remove
import majestic.icons.ic_student
import majestic.shared.profiles.Action

val switch = "On" to "Off"
fun commonPermissions(): List<Action> = listOf(
    Action(
        resource = Res.drawable.ic_student,
        title = "Enroll Students",
        description = "Complete registration, verify details, and confirm enrollment. " +
                "Sends confirmation via email/SMS.",
        active = true,
        switch = switch
    ),
    Action(
        resource = Res.drawable.ic_reminder,
        title = "Send Reminders",
        description = "Send follow-ups, resend emails/SMS, admission confirmations, " +
                "and rejection notices.",
        active = true,
        switch = switch
    ),
    Action(
        resource = Res.drawable.ic_remove,
        title = "Reject Applicants",
        description = "Reject unqualified applicants, revoke rejections, " +
                "and record rejection reasons.",
        active = true,
        switch = switch
    ),
    Action(
        resource = Res.drawable.ic_edit_user,
        title = "Admit Applicants",
        description = "Admit students, re-admit, process pending applications, " +
                "and view admission history.",
        active = true,
        switch = switch
    )
)