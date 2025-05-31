package com.example.lista

import android.os.Bundle
import com.example.lista.screens.TelaLogin
import com.example.lista.screens.TelaListaTarefas
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lista.ui.theme.ListaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ListaTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    App()
                }
            }
        }
    }
}
@Composable
fun App() {
    var telaAtual by remember { mutableStateOf("login") }

    when (telaAtual) {
        "login" -> TelaLogin(onLoginSucesso = { telaAtual = "lista" })
        "lista" -> TelaListaTarefas()
    }
}

@Composable
fun TelaLogin(onLoginSucesso: () -> Unit) {
    var usuario by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = usuario,
            onValueChange = { usuario = it },
            label = { Text("Usuário") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = senha,
            onValueChange = { senha = it },
            label = { Text("Senha") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (usuario.isNotEmpty() && senha.isNotEmpty()) {
                    onLoginSucesso()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Entrar")
        }
    }
}
@Composable
fun TarefaItem(tarefa: Tarefa, onToggleConcluida: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (tarefa.concluida) Color(0xFFF8BBD0) else Color.White
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Checkbox(
                checked = tarefa.concluida,
                onCheckedChange = { onToggleConcluida() },
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFFFF4081),
                    uncheckedColor = Color.Gray
                )
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = tarefa.descricao,
                    style = if (tarefa.concluida)
                        MaterialTheme.typography.bodyLarge.copy(
                            textDecoration = TextDecoration.LineThrough,
                            color = Color.DarkGray
                        )
                    else
                        MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Horário: ${tarefa.horario}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.ic_coelhinho),
                contentDescription = "Coelhinho",
                modifier = Modifier.size(36.dp)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewTelaLogin() {
    ListaTheme {
        TelaLogin {}
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTelaListaTarefas() {
    ListaTheme {
        TelaListaTarefas()
    }
}
