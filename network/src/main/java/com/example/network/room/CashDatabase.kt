package com.example.network.room

import androidx.room.*
import com.example.network.room.converter.CashRoomConverter

@Database(entities = [ProductEntity::class, SingleProduct::class], version = 1, exportSchema = false)
@TypeConverters(CashRoomConverter::class)
abstract class CashDatabase : RoomDatabase(){
    abstract val cashDao : CashDao
}

