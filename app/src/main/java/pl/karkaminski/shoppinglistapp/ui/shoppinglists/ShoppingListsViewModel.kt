package pl.karkaminski.shoppinglistapp.ui.shoppinglists

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

    fun insert(shoppingList: ShoppingList) {
        repository.insertShoppingList(shoppingList)
    }

    fun getAll(isActive:Boolean) : LiveData<List<ShoppingListWithDetails>> {
        return repository.getAllListsWithDetails(isActive)
    }
}