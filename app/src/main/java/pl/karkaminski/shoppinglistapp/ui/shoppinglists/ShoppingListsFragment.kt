package pl.karkaminski.shoppinglistapp.ui.shoppinglists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import pl.karkaminski.shoppinglistapp.data.ShoppingListWithDetails
import pl.karkaminski.shoppinglistapp.databinding.ShoppingListsFragmentBinding
import pl.karkaminski.shoppinglistapp.ui.ShoppingListsViewModel
import pl.karkaminski.shoppinglistapp.ui.mainviewpager.MainViewPagerFragmentDirections

class ShoppingListsFragment(private val showActive: Boolean) : Fragment(),
    ShoppingListsAdapter.OnItemClickedListener {

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
                val action =
                    MainViewPagerFragmentDirections.actionMainViewPagerFragmentToListDetailsFragment(null)
                findNavController().navigate(action)
            }
        }
        return fragmentBinding.root
    }

    override fun onItemClicked(shoppingListWithDetails: ShoppingListWithDetails) {
        val action = MainViewPagerFragmentDirections
            .actionMainViewPagerFragmentToListDetailsFragment(shoppingListWithDetails.shoppingList)

        findNavController().navigate(action)
    }
}