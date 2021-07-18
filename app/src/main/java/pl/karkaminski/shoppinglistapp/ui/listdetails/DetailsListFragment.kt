package pl.karkaminski.shoppinglistapp.ui.listdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import pl.karkaminski.shoppinglistapp.data.ShoppingListDetail
import pl.karkaminski.shoppinglistapp.data.ShoppingListWithDetails
import pl.karkaminski.shoppinglistapp.databinding.ListDetailsFragmentBinding

class DetailsListFragment : Fragment(), AddDetailDialog.AddDetailDialogListener{

    private val args by navArgs<DetailsListFragmentArgs>()
    private val listDetailsAdapter = DetailsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = ListDetailsFragmentBinding.inflate(inflater, container, false)

        if (args.shoppingListWithDetails!= null) {
            val shoppingList = args.shoppingListWithDetails!!
            fragmentBinding.editTextName.setText(shoppingList.listInfo.name)
        }

        fragmentBinding.recyclerView.adapter = listDetailsAdapter

        fragmentBinding.floatingActionButton.setOnClickListener {
            val dialog = AddDetailDialog(this)
            dialog.show(requireActivity().supportFragmentManager, "addDetailDialog")
        }

        return fragmentBinding.root
    }

    override fun addDetail(detailName: String, detailQuantity: Double) {
//        shoppingList.add(ShoppingListDetail(1, detailName, detailQuantity))
        listDetailsAdapter.notifyDataSetChanged()
    }
}