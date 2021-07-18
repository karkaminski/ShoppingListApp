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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = ListDetailsFragmentBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(ShoppingListsViewModel::class.java)

        if (args.shoppingListWithDetails != null) {
            shoppingListWithDetails = args.shoppingListWithDetails!!
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
                viewModel.insertShoppingList(shoppingListWithDetails!!.listInfo)
            }
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