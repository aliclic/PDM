package com.example.navegacao1.ui.telas

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.navegacao1.model.dados.Usuario
import com.example.navegacao1.model.dados.UsuarioDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TelaCadastro(
    modifier: Modifier = Modifier,
    onSignupComplete: () -> Unit // Função para navegar de volta para a tela de login após o cadastro
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var login by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var mensagemErro by remember { mutableStateOf<String?>(null) }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = login,
            onValueChange = { login = it },
            label = { Text(text = "Login") },
            isError = login.isBlank() && mensagemErro != null
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = senha,
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = { senha = it },
            label = { Text(text = "Senha") },
            isError = senha.isBlank() && mensagemErro != null
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if (login.isBlank() || senha.isBlank()) {
                    mensagemErro = "Todos os campos devem ser preenchidos!"
                } else {
                    scope.launch(Dispatchers.IO) {
                        val novoUsuario = Usuario(nome = login, senha = senha)
                        usuarioDAO.adicionar(novoUsuario) {
                            onSignupComplete() // Navegar de volta para a tela de login após o cadastro
                        }
                    }
                }
            }
        ) {
            Text("Cadastrar")
        }

        mensagemErro?.let {
            LaunchedEffect(it) {
                Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                mensagemErro = null
            }
        }
    }
}
