package pl.karkaminski.shoppinglistapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class ShoppingListDetail (
    val id: Int,
    val name: String,
    val quantity: Double,
    val isChecked: Boolean = false
    ) : Parcelable