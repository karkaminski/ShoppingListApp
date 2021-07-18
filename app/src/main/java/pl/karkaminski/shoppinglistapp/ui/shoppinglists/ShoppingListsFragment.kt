package pl.karkaminski.shoppinglistapp.ui.shoppinglists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import pl.karkaminski.shoppinglistapp.data.ShoppingList
import pl.karkaminski.shoppinglistapp.data.ShoppingListDetail
import pl.karkaminski.shoppinglistapp.data.ShoppingListWithDetails
import pl.karkaminski.shoppinglistapp.databinding.ShoppingListsFragmentBinding
import pl.karkaminski.shoppinglistapp.ui.ShoppingListsViewModel
import pl.karkaminski.shoppinglistapp.ui.dialogs.RenameDialog
import pl.karkaminski.shoppinglistapp.ui.mainviewpager.MainViewPagerFragmentDirections

class ShoppingListsFragment(private val showActive: Boolean) :
    Fragment(),
    ShoppingListsAdapter.OnItemClickedListener,
    RenameDialog.RenameListDialogListener{

    private lateinit var viewModel: ShoppingListsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = ShoppingListsFragmentBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(ShoppingListsViewModel::class.java)

        val shoppingListsAdapter = ShoppingListsAdapter(this)
        fragmentBinding.recyclerView.adapter = shoppingListsAdapter

        viewModel.getAllShoppingListsWithDetails(showActive).observe(
            viewLifecycleOwner
        ) { list ->
            if(list.isEmpty()){
                fragmentBinding.recyclerView.visibility = View.GONE
                fragmentBinding.textViewEmptyList.visibility = View.VISIBLE
            } else {
                fragmentBinding.recyclerView.visibility = View.VISIBLE
                fragmentBinding.textViewEmptyList.visibility = View.GONE
            }
            shoppingListsAdapter.apply {
                if (list != null) {
                    shoppingListsList = list
                    notifyDataSetChanged()
                }
            }
        }

        fragmentBinding.floatingActionButton.apply {
            isVisible = showActive
            setOnClickListener {
                createListWithDialog()
            }
        }
        return fragmentBinding.root
    }

    override fun onItemClicked(shoppingListWithDetails: ShoppingListWithDetails) {
        val action = MainViewPagerFragmentDirections
            .actionMainViewPagerFragmentToListDetailsFragment(shoppingListWithDetails.shoppingList)

        findNavController().navigate(action)
    }

    override fun renameList(listName: String) {
        val shoppingList = ShoppingList(listName)
        viewModel.insertShoppingList(shoppingList)

    }

    private fun createListWithDialog() {
        val dialog = RenameDialog(this)
        dialog.show(requireActivity().supportFragmentManager, "renameListDialog")
    }
}