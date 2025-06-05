package edu.com.reyphillnuez_ap2_p1.Data.Local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import edu.com.reyphillnuez_ap2_p1.Data.Local.dao.TareasDao
import edu.com.reyphillnuez_ap2_p1.Data.Local.entities.TareasEntity

@Database(
    entities =[
        TareasEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class parcial1: RoomDatabase()
{
    abstract fun tareasDao(): TareasDao
}