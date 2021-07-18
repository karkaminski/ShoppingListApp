package pl.karkaminski.shoppinglistapp.data

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import kotlinx.parcelize.Parcelize

@Parcelize
class ShoppingListWithDetails (
    @Embedded val listInfo : ShoppingList,
    @Relation(
        parentColumn = "shopping_list_id",
        entityColumn = "shopping_list_id"
    )
    var details: List<ShoppingListDetail>
) : Parcelable