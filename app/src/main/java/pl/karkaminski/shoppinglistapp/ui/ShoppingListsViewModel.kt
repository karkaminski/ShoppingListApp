package pl.karkaminski.shoppinglistapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pl.karkaminski.shoppinglistapp.data.AppDatabase
import pl.karkaminski.shoppinglistapp.data.ShoppingList
import pl.karkaminski.shoppinglistapp.data.ShoppingListRepository
import pl.karkaminski.shoppinglistapp.data.ShoppingListWithDetails

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

    fun getAll(isActive:Boolean) : LiveData<List<ShoppingListWithDetails>> {
        return repository.getAllListsWithDetails(isActive)
    }
}