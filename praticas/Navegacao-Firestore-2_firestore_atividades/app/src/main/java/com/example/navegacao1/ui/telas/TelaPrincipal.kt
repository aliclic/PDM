import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.navegacao1.model.dados.Usuario
import com.example.navegacao1.ui.telas.usuarioDAO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun TelaPrincipal(modifier: Modifier = Modifier, onLogoffClick: () -> Unit) {
    val scope = rememberCoroutineScope()
    val usuarios = remember { mutableStateListOf<Usuario>() }

    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = "Tela Principal",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                scope.launch(Dispatchers.IO) {
                    usuarioDAO.buscar { usuariosRetornados ->
                        usuarios.clear()
                        usuarios.addAll(usuariosRetornados)
                    }
                }
            }) {
                Text("Carregar")
            }
            Button(onClick = { onLogoffClick() }) {
                Text("Sair")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Carrega sob demanda à medida que o usuário rola na tela
        LazyColumn {
            items(usuarios) { usuario ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 8.dp),
                    elevation = CardDefaults.cardElevation(4.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = usuario.nome,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        // Adicione mais informações, se desejar
                    }
                }
            }
        }
    }
}
