package pl.karkaminski.shoppinglistapp.ui.listdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import pl.karkaminski.shoppinglistapp.data.ShoppingList
import pl.karkaminski.shoppinglistapp.data.ShoppingListWithDetails
import pl.karkaminski.shoppinglistapp.databinding.ListDetailsFragmentBinding
import pl.karkaminski.shoppinglistapp.ui.ShoppingListsViewModel

class DetailsListFragment : Fragment(), AddDetailDialog.AddDetailDialogListener {

    private lateinit var viewModel: ShoppingListsViewModel

    private var shoppingListWithDetails: ShoppingListWithDetails? = null
    private val args by navArgs<DetailsListFragmentArgs>()
    private val listDetailsAdapter = DetailsListAdapter()
    private var newList = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = ListDetailsFragmentBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(ShoppingListsViewModel::class.java)

        if (args.shoppingListWithDetails != null) {
            shoppingListWithDetails = args.shoppingListWithDetails!!
            newList = false
            fragmentBinding.editTextName.setText(shoppingListWithDetails!!.listInfo.name)
        }

        fragmentBinding.editTextName.setOnFocusChangeListener {
                viewEditTextName, hasFocus ->
            if (!hasFocus) {
                if (shoppingListWithDetails == null) {
                    shoppingListWithDetails = ShoppingListWithDetails(
                        ShoppingList((viewEditTextName as EditText).text.toString()),
                        arrayListOf()
                    )
                }
                else {
                    shoppingListWithDetails!!.listInfo.name = (viewEditTextName as EditText).text.toString()
                }
                insertOrUpdate(shoppingListWithDetails!!.listInfo)
            }
        }

        fragmentBinding.recyclerView.adapter = listDetailsAdapter

        fragmentBinding.floatingActionButton.setOnClickListener {
            val dialog = AddDetailDialog(this)
            dialog.show(requireActivity().supportFragmentManager, "addDetailDialog")
        }

        return fragmentBinding.root
    }

    private fun insertOrUpdate(shoppingList: ShoppingList) {
        if (newList) {
            viewModel.insertShoppingList(shoppingList)
        } else {
            viewModel.updateShoppingList(shoppingList)
        }
    }

    override fun addDetail(detailName: String, detailQuantity: Double) {
//        shoppingList.add(ShoppingListDetail(1, detailName, detailQuantity))
        listDetailsAdapter.notifyDataSetChanged()
    }
}