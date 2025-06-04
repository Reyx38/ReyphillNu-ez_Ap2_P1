package edu.com.reyphillnuez_ap2_p1.Data.Repository

import edu.com.reyphillnuez_ap2_p1.Data.Local.dao.TareasDao
import edu.com.reyphillnuez_ap2_p1.Data.Local.entities.TareasEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val taskDao: TareasDao
) {
    suspend fun save(tareasEntity: TareasEntity) = taskDao.save(tareasEntity)
    suspend fun find(id: Int?) = taskDao.find(id)
    suspend fun delete(tareasEntity: TareasEntity) = taskDao.delete(tareasEntity)
    fun getAll() = taskDao.getAll()
}