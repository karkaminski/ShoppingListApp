package pl.karkaminski.shoppinglistapp.ui.shoppinglists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pl.karkaminski.shoppinglistapp.data.ShoppingList
import pl.karkaminski.shoppinglistapp.data.shoppingLists

class ShoppingListsViewModel : ViewModel() {

    fun listsLiveData(showActive: Boolean): LiveData<ArrayList<ShoppingList>> {
        val mutableLiveData = MutableLiveData<ArrayList<ShoppingList>> ()
        val arrayList = arrayListOf<ShoppingList>()
        for (list in shoppingLists) {
            if(list.isActive == showActive){
                arrayList.add(list)
            }
        }
        mutableLiveData.postValue(arrayList)
        return mutableLiveData
    }
}