package majestic.users.tools.data

import majestic.shared.profiles.Permission
import majestic.shared.profiles.Permissions
import majestic.shared.users.data.Role
import org.jetbrains.compose.resources.DrawableResource

private val dateJoined = listOf("24 Feb 2025", "28 Mar 2025", "30 Apr 2025", "26 May 2025")
private val lastActive = listOf("24 Feb 2025", "28 Mar 2025", "30 Apr 2025", "26 May 2025")

private val maleFirstNames = listOf(
    "John", "Peter",
    "David", "Michael", "Robert",
    "James", "William", "Richard",
    "Thomas", "Christopher"
)
private val femaleFirstNames = listOf(
    "Jane", "Mary",
    "Sarah", "Elizabeth",
    "Jennifer", "Linda",
    "Patricia", "Susan",
    "Jessica", "Lisa"
)

private val lastNames = listOf(
    "Doe", "Smith",
    "Jones", "Williams",
    "Brown", "Johnson",
    "Davis", "Wilson",
    "Anderson", "Taylor"
)

private val emailDomains = listOf(
    "gmail.com", "outlook.com",
    "yahoo.com", "hotmail.com",
    "aol.com", "icloud.com"
)

data class UsersData(
    val id: String,
    var selected: Boolean,
    val userAvatar: DrawableResource? = null,
    val fullName: String,
    val email: String,
    val phone: Pair<String, DrawableResource>,
    val dateJoined: Pair<String, DrawableResource>,
    val dob: Pair<String, DrawableResource>,
    val lastActive: Pair<String, DrawableResource>,
    val gender: Gender,
    val flag: DrawableResource,
    val rolesCount: Int,
    val permissionsCount: Int,
    val permissions: List<Permission>,
    val roles: List<Role>,
    val status: UserStatus,
) {
    companion object {
        fun getUserData(
            avatars: List<DrawableResource?>,
            permissionList: List<Permissions>,
            roles: List<Role>,
            headerIcons: HeaderIcons
        ): UsersData {
            val (fullName, email, gender) = generateRandomUserWithGender()

            val randomPermissions =
                permissionList.flatMap { it.permissions }.shuffled().take((5..15).random())
            val randomRoles = roles.shuffled().take((1..roles.size).random())

            return UsersData(
                id = "019975ac-2e24-ba77-e7017690f${(0..69).random()}b",
                selected = false,
                userAvatar = avatars.random(),
                fullName = fullName,
                email = email,
                dateJoined = Pair(dateJoined.random(), headerIcons.calendar),
                lastActive = Pair(lastActive.random(), headerIcons.time),
                dob = Pair(dateJoined.random(), headerIcons.calendar),
                rolesCount = randomRoles.size,
                roles = randomRoles,
                permissionsCount = randomPermissions.size,
                permissions = randomPermissions,
                status = UserStatus.entries.random(),
                gender = gender,
                phone = Pair(
                    buildString { append("07"); repeat(8) { append((0..9).random()) } },
                    headerIcons.phone
                ),
                flag = headerIcons.flag
            )
        }

        private fun generateRandomUserWithGender(): Triple<String, String, Gender> {
            val chosenGender = if ((0..1).random() == 0) Gender.MALE else Gender.FEMALE

            val firstName = when (chosenGender) {
                Gender.MALE -> maleFirstNames.random()
                Gender.FEMALE -> femaleFirstNames.random()
            }

            val lastName = lastNames.random()
            val fullName = "$firstName $lastName"

            val email = generateEmail(firstName, lastName)

            return Triple(fullName, email, chosenGender)
        }

        private fun generateEmail(firstName: String, lastName: String): String {
            val emailVariations = listOf(
                "${firstName.lowercase()}.${lastName.lowercase()}",
                "${firstName.lowercase()}_${lastName.lowercase()}",
                "${firstName.first().lowercase()}${lastName.lowercase()}",
                "${firstName.lowercase()}${lastName.first().lowercase()}",
                "${firstName.lowercase()}${(100..999).random()}"
            )

            return "${emailVariations.random()}@${emailDomains.random()}"
        }

        private fun generateRandomDob(): String {
            val year = (1994..2004).random()
            val monthIndex = (1..12).random()
            val maxDay = when (monthIndex) {
                1, 3, 5, 7, 8, 10, 12 -> 31
                4, 6, 9, 11 -> 30
                2 -> if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) 29 else 28
                else -> 30
            }
            val day = (1..maxDay).random()
            val months = listOf(
                "Jan",
                "Feb",
                "Mar",
                "Apr",
                "May",
                "Jun",
                "Jul",
                "Aug",
                "Sep",
                "Oct",
                "Nov",
                "Dec"
            )
            return "$day ${months.random()} $year"
        }
    }
}