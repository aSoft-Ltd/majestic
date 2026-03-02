package majestic.shared.profiles.roles.data

import majestic.icons.Res
import majestic.icons.ic_student
import majestic.shared.profiles.Permission
import majestic.shared.users.data.permissions
import org.jetbrains.compose.resources.DrawableResource
import kotlin.random.Random

data class Role(
    val resource: DrawableResource,
    val title: String,
    val description: String,
    val responsibilities: List<Permission>
) {
    companion object {
        fun roles() = listOf(
            Role(
                resource = Res.drawable.ic_student,
                title = "Class 1 Kiswahili Subject Teacher",
                description = "Add new applicants, Editing Applications, Viewer, Enroll new students, Admitting Applicants, Rejecting applicants & Can capture admission Payments",
                responsibilities = permissions().take(Random.nextInt(permissions().size + 3))
            ),
            Role(
                resource = Res.drawable.ic_student,
                title = "Class 5 Mathematics Teacher",
                description = "Conducting classes, Grading assignments, Developing curriculum, Organizing group activities, Assessing student performance, Mentoring students & Providing feedback.",
                responsibilities = permissions().take(Random.nextInt(permissions().size + 3))
            ),
            Role(
                resource = Res.drawable.ic_student,
                title = "IT Specialist",
                description = "Managing IT infrastructure, Troubleshooting technical issues, Implementing software solutions, Training staff on technology, Ensuring cybersecurity & Maintaining databases.",
                responsibilities = permissions().take(Random.nextInt(permissions().size + 3))
            ),
            Role(
                resource = Res.drawable.ic_student,
                title = "Librarian",
                description = "Organizing library resources, Assisting students with research, Managing book inventory, Promoting literacy programs, Conducting library orientation & Collaborating with teachers on resource needs",
                responsibilities = permissions().take(Random.nextInt(permissions().size + 3))
            )
        )
    }
}