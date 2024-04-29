import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@Composable
@Preview
fun Login() {

    var user by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val buttonEnabled = user.isNotEmpty() && password.isNotEmpty()

    MaterialTheme {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp, alignment = Alignment.CenterVertically),
            modifier = Modifier.fillMaxSize(),
        ){

            OutlinedTextField(
                value = user,
                onValueChange = {
                    if (it.length < 30) {
                        user = it
                    }
                },
                label = {Text("User")},
                maxLines = 1

            )

            var passVisible by remember { mutableStateOf(false) }

            OutlinedTextField(
                value = password,
                onValueChange = {
                    if (it.length < 30) {
                        password = it
                    }
                },
                label = {Text("Password")},
                visualTransformation = if(passVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconToggleButton(checked = passVisible, onCheckedChange = {passVisible = it}){
                        Icon(
                            imageVector = if (passVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = null
                        )
                    }
                }

            )
            Button(
                onClick = {user = ""; password = ""},
                enabled = buttonEnabled
            ) {
                Text(text = "Login")
            }
        }
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Iniciar sesión") {
        Login()
    }
}
