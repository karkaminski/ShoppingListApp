package pl.karkaminski.shoppinglistapp.ui.shoppinglists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import pl.karkaminski.shoppinglistapp.data.ShoppingList
import pl.karkaminski.shoppinglistapp.data.shoppingLists
import pl.karkaminski.shoppinglistapp.databinding.ShoppingListsFragmentBinding
import pl.karkaminski.shoppinglistapp.ui.mainviewpager.MainViewPagerFragmentDirections

class ShoppingListsFragment(private val showActive: Boolean) : Fragment(),
    ShoppingListsAdapter.OnItemClickedListener {

    private val viewModel by viewModels<ShoppingListsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = ShoppingListsFragmentBinding.inflate(inflater, container, false)

        val shoppingListsAdapter = ShoppingListsAdapter(this)
        shoppingListsAdapter.shoppingListsList = shoppingLists
        fragmentBinding.recyclerView.adapter = shoppingListsAdapter

        viewModel.listsLiveData(showActive).observe(viewLifecycleOwner,
            { list ->
                shoppingListsAdapter.apply {
                    if (list != null) {
                        shoppingListsList = list
                        notifyDataSetChanged()
                    }
                }
            })

        return fragmentBinding.root
    }

    override fun onItemClicked(shoppingList: ShoppingList) {
        val action = MainViewPagerFragmentDirections.actionMainViewPagerFragmentToListDetailsFragment()
        findNavController().navigate(action)
    }
}