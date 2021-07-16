package pl.karkaminski.shoppinglistapp.ui.listdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.karkaminski.shoppinglistapp.data.ShoppingListDetail
import pl.karkaminski.shoppinglistapp.databinding.ListItemShoppingDetailBinding

class DetailsListAdapter : RecyclerView.Adapter<DetailsListAdapter.ListDetailsViewHolder> (){

    var detailsList = listOf<ShoppingListDetail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDetailsViewHolder {
        return ListDetailsViewHolder(
            ListItemShoppingDetailBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ListDetailsViewHolder, position: Int) {
        val shoppingListDetail = detailsList[position]

        holder.binding.apply {
            textViewName.text = shoppingListDetail.name
            textViewQuantity.text = shoppingListDetail.quantity.toString()
            checkBox.isChecked = shoppingListDetail.isChecked
        }
    }

    override fun getItemCount() = detailsList.size

    inner class ListDetailsViewHolder(val binding: ListItemShoppingDetailBinding)
        : RecyclerView.ViewHolder(binding.root)
}