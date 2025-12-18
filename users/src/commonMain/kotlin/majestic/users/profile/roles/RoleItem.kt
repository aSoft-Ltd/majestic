package com.example.demo.roles

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

sealed interface Assignment
object Assigned : Assignment
object UnAssigned : Assignment

val assignments = listOf(Assigned, UnAssigned)

data class Role(
    val name: String,
    val description: String,
    val assignment: Assignment = assignments.random(),
    val actionType: ActionType = ActionType.ASSIGNMENT
)

enum class ActionType {
    ASSIGNMENT, SETUP
}

@Composable
fun RoleItem(
    index: Int,
    name: String,
    description: String,
    isRoleAssignment: Assignment = Assigned,
    onPermissionsClick: () -> Unit,
    onClick: () -> Unit,
    actionType: ActionType = ActionType.ASSIGNMENT,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp), modifier = Modifier.fillMaxWidth(0.7f)) {
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = (index + 1).toString() + ".", fontSize = 16.sp, color = Color.White.copy(alpha = 0.5f)
                )
                Text(
                    text = name, fontSize = 16.sp, color = Color.White
                )
            }
            Text(
                text = description, fontSize = 14.sp, color = Color.White.copy(alpha = 0.5f)
            )
            Text(
                text = "View Permissions",
                fontSize = 14.sp,
                color = Color.White,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable { onPermissionsClick() })
        }

        Button(
            onClick = onClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White.copy(alpha = 0.20f), contentColor = when (isRoleAssignment) {
                    is UnAssigned -> Color.White
                    is Assigned -> Color.Red
                }
            ),
            border = if (actionType == ActionType.SETUP) {
                BorderStroke(1.dp, Color.White)
            } else null,
            modifier = Modifier.width(120.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = if (actionType == ActionType.SETUP) "Setup" else {
                    when (isRoleAssignment) {
                        is UnAssigned -> "Assign"
                        is Assigned -> "UnAssign"
                    }
                }
            )
        }
    }
}