package pl.karkaminski.shoppinglistapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingListDao {

    @Insert
    fun insert(shoppingList: ShoppingList)

    @Update
    fun update(shoppingList: ShoppingList)

    @Delete
    fun delete(shoppingList: ShoppingList)

    @Query("SELECT * FROM ShoppingList WHERE is_active = 1")
    fun getAllActive(): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM ShoppingList WHERE is_active = 0")
    fun getAllInactive(): LiveData<List<ShoppingList>>
}

