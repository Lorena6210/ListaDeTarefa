package com.example.lista.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import com.example.lista.model.Tarefa

@Dao
interface TarefaDao {

    @Query("SELECT * FROM tarefas")
    fun getTodas(): Flow<List<Tarefa>>

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    suspend fun inserir(tarefa: Tarefa)

    @Update
    suspend fun atualizar(tarefa: Tarefa)

    @Delete
    suspend fun deletar(tarefa: Tarefa)
}
