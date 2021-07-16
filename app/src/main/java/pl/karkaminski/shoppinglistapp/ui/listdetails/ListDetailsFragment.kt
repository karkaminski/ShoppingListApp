package pl.karkaminski.shoppinglistapp.ui.listdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import pl.karkaminski.shoppinglistapp.databinding.ListDetailsFragmentBinding

class ListDetailsFragment : Fragment() {

    private val args by navArgs<ListDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = ListDetailsFragmentBinding.inflate(inflater, container, false)

        val listDetailsAdapter = ListDetailsAdapter()
        listDetailsAdapter.detailsList = args.shoppingList.details
        fragmentBinding.recyclerView.adapter = listDetailsAdapter
        return fragmentBinding.root
    }


}