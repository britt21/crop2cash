package com.example.network.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.common.model.Products
import kotlinx.coroutines.flow.Flow

@Dao
interface CashDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertProducts(products: ProductEntity)

    @Query("Select * From productentity")
     fun getProducts(): LiveData<List<ProductEntity>>
}