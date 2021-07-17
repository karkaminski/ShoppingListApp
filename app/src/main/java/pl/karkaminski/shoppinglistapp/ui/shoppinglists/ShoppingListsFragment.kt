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
import pl.karkaminski.shoppinglistapp.databinding.ShoppingListsFragmentBinding
import pl.karkaminski.shoppinglistapp.ui.mainviewpager.MainViewPagerFragmentDirections

class ShoppingListsFragment(private val showActive: Boolean) : Fragment(),
    ShoppingListsAdapter.OnItemClickedListener {

    private lateinit var viewModel: ShoppingListsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = ShoppingListsFragmentBinding.inflate(inflater, container, false)

        val shoppingListsAdapter = ShoppingListsAdapter(this)
        fragmentBinding.recyclerView.adapter = shoppingListsAdapter

        viewModel = ViewModelProvider(this).get(ShoppingListsViewModel::class.java)

        viewModel.getAll(showActive).observe(
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
                viewModel.insert(ShoppingList("Example list"))
//                val action =
//                    MainViewPagerFragmentDirections.actionMainViewPagerFragmentToListDetailsFragment(null)
//                findNavController().navigate(action)
            }
        }
        return fragmentBinding.root
    }

    override fun onItemClicked(shoppingList: ShoppingList) {
        val action = MainViewPagerFragmentDirections
            .actionMainViewPagerFragmentToListDetailsFragment(shoppingList)

        findNavController().navigate(action)
    }
}