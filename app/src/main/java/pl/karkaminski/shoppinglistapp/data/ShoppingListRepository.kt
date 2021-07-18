package pl.karkaminski.shoppinglistapp.data

import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class ShoppingListRepository(
    val shoppingListDao: ShoppingListDao,
    val shoppingListDetailDao: ShoppingListDetailDao
) {

    fun insertShoppingList(shoppingList: ShoppingList) {
        CoroutineScope(IO).launch { shoppingListDao.insert(shoppingList) }
    }

    fun updateShoppingList(shoppingList: ShoppingList) {
        CoroutineScope(IO).launch { shoppingListDao.update(shoppingList) }
    }

    fun insertDetail(shoppingListDetail: ShoppingListDetail) {
        CoroutineScope(IO).launch { shoppingListDetailDao.insert(shoppingListDetail) }
    }

    fun updateDetail(shoppingListDetail: ShoppingListDetail) {
        CoroutineScope(IO).launch { shoppingListDetailDao.update(shoppingListDetail) }
    }

    fun getAllListsWithDetails(isActive: Boolean): LiveData<List<ShoppingListWithDetails>> {
        return if (isActive) shoppingListDao.getAllActiveWithDetails() else shoppingListDao.getAllInactiveWithDetails()
    }

    fun insertDetailForList(shoppingList: ShoppingList, shoppingListDetail: ShoppingListDetail) {
        CoroutineScope(IO).launch {
            shoppingListDetail.listId = shoppingList.id
            shoppingListDetailDao.insert(shoppingListDetail)
        }
    }

    fun getAllDetailsForShoppingList(shoppingList: ShoppingList): LiveData<List<ShoppingListDetail>> {
        return shoppingListDetailDao.getAllFromList(shoppingList.id)
    }
}