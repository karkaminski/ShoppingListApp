package pl.karkaminski.shoppinglistapp.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ShoppingListRepository(val shoppingListDao: ShoppingListDao) {

    fun insert(shoppingList: ShoppingList) {
        CoroutineScope(IO).launch { shoppingListDao.insert(shoppingList) }

    }

    suspend fun update(shoppingList: ShoppingList) =
        shoppingListDao.update(shoppingList)

    suspend fun delete(shoppingList: ShoppingList) =
        shoppingListDao.delete(shoppingList)

    fun getAll(isActive:Boolean): LiveData<List<ShoppingList>> {
        return if (isActive) shoppingListDao.getAllActive() else shoppingListDao.getAllInactive()
    }
}