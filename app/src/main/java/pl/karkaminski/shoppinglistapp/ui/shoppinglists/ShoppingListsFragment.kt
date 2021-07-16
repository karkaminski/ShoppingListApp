package pl.karkaminski.shoppinglistapp.ui.shoppinglists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import pl.karkaminski.shoppinglistapp.data.shoppingLists
import pl.karkaminski.shoppinglistapp.databinding.ShoppingListsFragmentBinding

class ShoppingListsFragment(private val showActive: Boolean) : Fragment() {

    private val viewModel by viewModels<ShoppingListsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = ShoppingListsFragmentBinding.inflate(inflater, container, false)

        val shoppingListsAdapter = ShoppingListsAdapter()
        shoppingListsAdapter.shoppingListsList = shoppingLists
        fragmentBinding.recyclerView.adapter = shoppingListsAdapter

        viewModel.listsLiveData(showActive).observe(viewLifecycleOwner,
            { list ->
                shoppingListsAdapter.apply {
                    if (list != null){
                        shoppingListsList = list
                        notifyDataSetChanged()
                    }
                }
            })

        return fragmentBinding.root
    }

}