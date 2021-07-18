package pl.karkaminski.shoppinglistapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(shoppingList: ShoppingList)

    @Update
    fun update(shoppingList: ShoppingList)

    @Query("SELECT * FROM ShoppingList WHERE is_active = 1")
    fun getAllActive(): LiveData<List<ShoppingList>>

    @Query("SELECT * FROM ShoppingList WHERE is_active = 0")
    fun getAllInactive(): LiveData<List<ShoppingList>>

    @Transaction
    @Query("SELECT * FROM ShoppingList")
    fun getAllActiveWithDetails(): LiveData<List<ShoppingListWithDetails>>

    @Transaction
    @Query("SELECT * FROM ShoppingList")
    fun getAllInactiveWithDetails(): LiveData<List<ShoppingListWithDetails>>
}

