package com.example.lista.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.lista.data.TarefaDao
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class TarefaViewModel(private val dao: TarefaDao) : ViewModel() {
    val tarefas: StateFlow<List<Tarefa>> = dao.getTodas()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun adicionarTarefa(descricao: String, horario: String) {
        viewModelScope.launch {
            dao.inserir(Tarefa(descricao = descricao, horario = horario))
        }
    }

    fun alternarConcluida(tarefa: Tarefa) {
        viewModelScope.launch {
            dao.atualizar(tarefa.copy(concluida = !tarefa.concluida))
        }
    }
}

class TarefaViewModelFactory(private val dao: TarefaDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TarefaViewModel(dao) as T
    }
}
