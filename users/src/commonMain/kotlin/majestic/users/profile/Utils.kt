package majestic.users.profile

import tz.co.asoft.academia.admissions.dashboard.bulk.review.enrolled.tools.EnrolledData
import tz.co.asoft.academia.admissions.dashboard.bulk.review.tools.recall.Action
import tz.co.asoft.academia.admissions.dashboard.bulk.review.tools.recall.FormData
import tz.co.asoft.academia.admissions.dashboard.bulk.review.tools.recall.ListItem
import tz.co.asoft.academia.admissions.dashboard.bulk.review.tools.recall.ribbon.RibbonItem

internal fun enrolledData(
    data: EnrolledData,
    onSubmit: () -> Unit,
    onCancel: () -> Unit
) = FormData(
    ribbon = listOf(
        RibbonItem(
            title = "Enrollment No.",
            subtitle = data.id
        ),
        RibbonItem(
            title = "Performed By",
            subtitle = data.performedBy
        ),
        RibbonItem(
            title = "Date Performed",
            subtitle = "${data.datePerformed} - ${data.timePerformed}"
        ),
        RibbonItem(
            title = "No.Enrolled",
            subtitle = "${data.totalApplicants} Students"
        ),
    ),
    title = "Reason For Recall",
    reasons = listOf(
        "Select Recall Reason",
        "Wrong class/level selected",
        "Wrong student group admitted",
        "Instructions error/misscommunication",
        "Technical/system mistake",
        "Duplicate enrollment",
        "Others"
    ),
    listItems = data.applicants.map { applicant ->
        ListItem(
            avatar = applicant.avatar,
            name = applicant.fullName,
            country = applicant.country,
            email = applicant.email,
            phone = applicant.phone
        )
    },
    onCancel = Action("Cancel", onCancel),
    onSubmit = Action("Submit", onSubmit)
)