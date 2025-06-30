package org.mktech.tictactoe.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.mktech.tictactoe.theme.buttonColor
import org.mktech.tictactoe.theme.dialogColor

@Composable
fun CustomDialog(
    showDialog: Boolean,
    title: String,
    msg: String,
    onDismissRequest: () -> Unit,
) {


    if (showDialog) {
        Dialog(
            onDismissRequest = { onDismissRequest },
            properties = DialogProperties(
                dismissOnBackPress = true, dismissOnClickOutside = true
            ),
        ) {
            ElevatedCard(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                colors = CardDefaults.elevatedCardColors(dialogColor)
            ) {

                Column(
                    modifier = Modifier.padding(all = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color.White,
                        fontSize = 18.sp
                    )
                    Text(
                        text = msg,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                    )
                    Button(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(buttonColor)
                    ) {
                        Text("OK")
                    }
                }
            }
        }
    }

}

@Preview
@Composable
fun DialogPreview() {
    var showDialog by remember { mutableStateOf(true) }
    if (showDialog) {
        CustomDialog(
            showDialog = showDialog,
            title = "Hello Manish",
            msg = "What r u doing today, i am very glad to see you",
            onDismissRequest = { showDialog = false })
    }
}
