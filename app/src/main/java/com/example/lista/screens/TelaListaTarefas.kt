package com.example.lista.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.example.lista.R
import com.example.lista.viewModel.TarefaViewModel

@JvmOverloads
@Composable
fun TelaListaTarefas(viewModel: TarefaViewModel = TarefaViewModel()) {
    val tarefas by viewModel.tarefas.observeAsState(emptyList())

    var novaDescricao by remember { mutableStateOf("") }
    var novoHorario by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Lista de Tarefas", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = novaDescricao,
            onValueChange = { novaDescricao = it },
            label = { Text("Descrição da tarefa") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = novoHorario,
            onValueChange = { novoHorario = it },
            label = { Text("Horário (ex: 14:00)") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (novaDescricao.isNotBlank() && novoHorario.isNotBlank()) {
                    viewModel.adicionarTarefa(novaDescricao.trim(), novoHorario.trim())
                    novaDescricao = ""
                    novoHorario = ""
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF4081)
            ),
            modifier = Modifier.fillMaxWidth(),
            shape = CircleShape
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_coelhinho),
                contentDescription = "Coelhinho",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("Adicionar Tarefa", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(tarefas) { tarefa ->
                TarefaItem(tarefa = tarefa) {
                    viewModel.alternarConclusao(tarefa)
                }
            }
        }
    }
}

fun TarefaViewModel(): TarefaViewModel {
    TODO("Not yet implemented")
}
