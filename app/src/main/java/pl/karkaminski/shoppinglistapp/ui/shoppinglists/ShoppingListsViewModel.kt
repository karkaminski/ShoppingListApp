package pl.karkaminski.shoppinglistapp.ui.shoppinglists

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import pl.karkaminski.shoppinglistapp.data.AppDatabase
import pl.karkaminski.shoppinglistapp.data.ShoppingList
import pl.karkaminski.shoppinglistapp.data.ShoppingListRepository

class ShoppingListsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ShoppingListRepository

    init {
        val shoppingListDao =
            AppDatabase.getDatabase(application.applicationContext).shoppingListDao()
        repository = ShoppingListRepository(shoppingListDao)
    }

    fun insert(shoppingList: ShoppingList) {
        repository.insert(shoppingList)
    }

    fun getAll(isActive:Boolean) : LiveData<List<ShoppingList>> {
        return repository.getAll(isActive)
    }
}