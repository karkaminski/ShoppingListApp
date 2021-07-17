package pl.karkaminski.shoppinglistapp.ui.shoppinglists

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.karkaminski.shoppinglistapp.data.ShoppingList
import pl.karkaminski.shoppinglistapp.databinding.ListItemShoppingListBinding

class ShoppingListsAdapter(private var onClickListener : OnItemClickedListener)
    : RecyclerView.Adapter<ShoppingListsAdapter.ShoppingListsViewHolder>() {

    var shoppingListsList = listOf<ShoppingList>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingListsViewHolder {
        return ShoppingListsViewHolder(
            ListItemShoppingListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShoppingListsViewHolder, position: Int) {

        holder.binding.apply {
            shoppingList = shoppingListsList[position]

            root.setOnClickListener {
                onClickListener.onItemClicked(shoppingListsList[position])
            }
        }
    }

    override fun getItemCount() = shoppingListsList.size

    inner class ShoppingListsViewHolder(val binding: ListItemShoppingListBinding)
        : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickedListener{
        fun onItemClicked(shoppingList : ShoppingList)
    }
}