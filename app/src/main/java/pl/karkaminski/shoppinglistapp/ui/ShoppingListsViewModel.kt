package pl.karkaminski.shoppinglistapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pl.karkaminski.shoppinglistapp.data.*

class ShoppingListsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ShoppingListRepository

    init {
        val shoppingListDao =
            AppDatabase.getDatabase(application.applicationContext).shoppingListDao()
        val shoppingListDetailDao =
            AppDatabase.getDatabase(application.applicationContext).shoppingListDetailDao()
        repository = ShoppingListRepository(shoppingListDao, shoppingListDetailDao)
    }

    fun insertShoppingList(shoppingList: ShoppingList) {
        repository.insertShoppingList(shoppingList)
    }

    fun updateShoppingList(shoppingList: ShoppingList) {
        repository.updateShoppingList(shoppingList)
    }

    fun getAllShoppingListsWithDetails(isActive:Boolean) : LiveData<List<ShoppingListWithDetails>> {
        return repository.getAllListsWithDetails(isActive)
    }

    fun insertDetailForShoppingList(shoppingList: ShoppingList, shoppingListDetail: ShoppingListDetail){
        repository.insertDetailForList(shoppingList, shoppingListDetail)
    }

    fun getAllDetailsForShoppingList(shoppingList: ShoppingList) : LiveData<List<ShoppingListDetail>>{
        return repository.getAllDetailsForShoppingList(shoppingList)
    }

    fun updateDetail(shoppingListDetail: ShoppingListDetail) {
        repository.updateDetail(shoppingListDetail)
    }

}