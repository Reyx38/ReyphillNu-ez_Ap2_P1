package edu.com.reyphillnuez_ap2_p1.Data.Local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import edu.com.reyphillnuez_ap2_p1.Data.Local.entities.TareasEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TareasDao {
    @Upsert
    suspend fun save(taskEntity: TareasEntity)
    @Query(
        """
            Select * from
            Tareas where tareaId=:id
        """
    )
    suspend fun find(id: Int?) : TareasEntity?
    @Delete
    suspend fun delete(taskEntity: TareasEntity)
    @Query("""select * from tareas""")
    fun getAll(): Flow<List<TareasEntity>>
}