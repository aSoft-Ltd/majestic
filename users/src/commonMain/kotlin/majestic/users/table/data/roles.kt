package majestic.users.table.data

import majestic.icons.Res
import majestic.icons.ic_student
import users.data.Role

fun roles() = listOf(
    Role(
        resource = Res.drawable.ic_student,
        title = "Class 1 Kiswahili Subject Teacher",
        description = "Add new applicants, Editing Applications, Viewer, Enroll new students, Admitting Applicants, Rejecting applicants & Can capture admission Payments",
        roles = listOf(
            "Add new applicants",
            "Editing Applications",
            "Viewer",
            "Enroll new students",
            "Admitting Applicants",
            "Rejecting applicants",
            "Can capture admission Payments"
        )
    ),
    Role(
        resource = Res.drawable.ic_student,
        title = "Class 5 Mathematics Teacher",
        description = "Conducting classes, Grading assignments, Developing curriculum, Organizing group activities, Assessing student performance, Mentoring students & Providing feedback.",
        roles = listOf(
            "Conducting classes",
            "Grading assignments",
            "Developing curriculum",
            "Organizing group activities",
            "Assessing student performance",
            "Mentoring students",
            "Providing feedback"
        )
    ),
    Role(
        resource = Res.drawable.ic_student,
        title = "IT Specialist",
        description = "Managing IT infrastructure, Troubleshooting technical issues, Implementing software solutions, Training staff on technology, Ensuring cybersecurity & Maintaining databases.",
        roles = listOf(
            "Managing IT infrastructure",
            "Troubleshooting technical issues",
            "Implementing software solutions",
            "Training staff on technology",
            "Ensuring cybersecurity",
            "Maintaining databases"
        )
    ),
    Role(
        resource = Res.drawable.ic_student,
        title = "Librarian",
        description = "Organizing library resources, Assisting students with research, Managing book inventory, Promoting literacy programs, Conducting library orientation & Collaborating with teachers on resource needs",
        roles = listOf(
            "Organizing library resources",
            "Assisting students with research",
            "Managing book inventory",
            "Promoting literacy programs",
            "Conducting library orientation",
            "Collaborating with teachers on resource needs"
        )
    )
)