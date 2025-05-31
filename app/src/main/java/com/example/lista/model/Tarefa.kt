package com.example.lista.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tarefas")
data class Tarefa(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val descricao: String,
    val horario: String,
    val concluida: Boolean = false
)
