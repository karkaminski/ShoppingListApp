package pl.karkaminski.shoppinglistapp.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import org.jetbrains.annotations.NotNull

@Parcelize
@Entity(tableName = "ShoppingListDetail")
class ShoppingListDetail (
    @NotNull
    val name: String,

    val quantity: Double = 0.0,

    @NotNull
    @ColumnInfo(name = "is_checked")
    val isChecked: Boolean = false
    ) : Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "detail_id")
    var id: Int? = null

    @ColumnInfo(name = "shopping_list_id")
    var listId: Int? = null
    }