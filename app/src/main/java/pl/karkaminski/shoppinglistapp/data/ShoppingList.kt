package pl.karkaminski.shoppinglistapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ShoppingList (
    val id: Int,
    val name: String,
    val details: ArrayList<ShoppingListDetail>,
    val isActive: Boolean
    ) : Parcelable