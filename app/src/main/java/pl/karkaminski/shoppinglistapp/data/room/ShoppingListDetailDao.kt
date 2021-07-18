package pl.karkaminski.shoppinglistapp.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShoppingListDetailDao {

    @Insert
    fun insert(shoppingListDetail: ShoppingListDetail)

    @Update
    fun update(shoppingListDetail: ShoppingListDetail)

    @Delete
    fun delete(shoppingListDetail: ShoppingListDetail)

    @Query("SELECT * FROM ShoppingListDetail")
    fun getAll(): LiveData<List<ShoppingListDetail>>

    @Query("SELECT * FROM ShoppingListDetail WHERE shopping_list_id = :shoppingListId")
    fun getAllFromList(shoppingListId: Int?): LiveData<List<ShoppingListDetail>>

}