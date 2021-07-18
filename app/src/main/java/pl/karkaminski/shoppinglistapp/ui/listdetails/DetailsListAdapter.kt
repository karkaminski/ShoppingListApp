package pl.karkaminski.shoppinglistapp.ui.listdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pl.karkaminski.shoppinglistapp.data.ShoppingListDetail
import pl.karkaminski.shoppinglistapp.databinding.ListItemShoppingDetailBinding

class DetailsListAdapter(
    private var onItemClickedListener: OnItemClickedListener,
    var isListActive: Boolean = true) : RecyclerView.Adapter<DetailsListAdapter.ListDetailsViewHolder> (){

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
        var shoppingListDetail = detailsList[position]
        holder.binding.shoppingListDetail = shoppingListDetail

        holder.binding.checkBox.apply{
            isEnabled = isListActive
            setOnCheckedChangeListener { _, isChecked ->
                shoppingListDetail.isChecked = isChecked
                onItemClickedListener.onItemClicked(shoppingListDetail)
            }
        }
    }

    override fun getItemCount() = detailsList.size

    inner class ListDetailsViewHolder(val binding: ListItemShoppingDetailBinding)
        : RecyclerView.ViewHolder(binding.root)

    interface OnItemClickedListener {
        fun onItemClicked(shoppingListDetail: ShoppingListDetail)
    }
}