package pl.karkaminski.shoppinglistapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull

@Parcelize
@Entity
data class ShoppingList(
    @NotNull
    var name: String,

    @NotNull
    @ColumnInfo(name = "is_active")
    var isActive: Boolean = true
) : Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "shopping_list_id")
    var id: Int? = null
}