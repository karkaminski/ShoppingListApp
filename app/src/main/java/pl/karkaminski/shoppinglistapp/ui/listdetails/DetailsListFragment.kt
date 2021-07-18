package pl.karkaminski.shoppinglistapp.ui.listdetails

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import pl.karkaminski.shoppinglistapp.R
import pl.karkaminski.shoppinglistapp.data.ShoppingList
import pl.karkaminski.shoppinglistapp.data.ShoppingListDetail
import pl.karkaminski.shoppinglistapp.databinding.ListDetailsFragmentBinding
import pl.karkaminski.shoppinglistapp.ui.ShoppingListsViewModel
import pl.karkaminski.shoppinglistapp.ui.dialogs.AddDetailDialog
import pl.karkaminski.shoppinglistapp.ui.dialogs.RenameDialog

class DetailsListFragment : Fragment(),
    AddDetailDialog.AddDetailDialogListener,
    RenameDialog.RenameListDialogListener,
    DetailsListAdapter.OnItemClickedListener {

    private lateinit var mViewModel: ShoppingListsViewModel

    private lateinit var mShoppingList: ShoppingList

    private val args by navArgs<DetailsListFragmentArgs>()
    private val listDetailsAdapter = DetailsListAdapter(this)
    private var newList = false
    private lateinit var fragmentBinding: ListDetailsFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentBinding = ListDetailsFragmentBinding.inflate(inflater, container, false)

        mViewModel = ViewModelProvider(this).get(ShoppingListsViewModel::class.java)
        mShoppingList = args.shoppingList!!

        fragmentBinding.editTextName.setText(mShoppingList.name)

        fragmentBinding.recyclerView.adapter = listDetailsAdapter
        listDetailsAdapter.isListActive = mShoppingList.isActive

        mViewModel.getAllDetailsForShoppingList(mShoppingList).observe(
            viewLifecycleOwner
        ) { list ->
            if(list.isEmpty()){
                fragmentBinding.recyclerView.visibility = View.GONE
                fragmentBinding.textViewEmptyList.visibility = View.VISIBLE
            } else {
                fragmentBinding.recyclerView.visibility = View.VISIBLE
                fragmentBinding.textViewEmptyList.visibility = View.GONE
            }
            listDetailsAdapter.apply {
                if (list != null) {
                    detailsList = list
                    notifyDataSetChanged()

                }
            }
        }

        if (mShoppingList.isActive) {
            fragmentBinding.floatingActionButton.visibility = View.VISIBLE
        } else {
            fragmentBinding.floatingActionButton.visibility = View.GONE
        }

        fragmentBinding.floatingActionButton.setOnClickListener {
            val dialog = AddDetailDialog(this)
            dialog.show(requireActivity().supportFragmentManager, "addDetailDialog")
        }

        return fragmentBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_details_menu, menu)
        val itemArchive = menu.findItem(R.id.archive_list_item)
        itemArchive.setVisible(!newList && mShoppingList.isActive)
        val itemRename = menu.findItem(R.id.rename_item)
        itemRename.setVisible(!newList && mShoppingList.isActive)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.archive_list_item) {
            mShoppingList.isActive = false
            mViewModel.updateShoppingList(mShoppingList)
            val action =
                DetailsListFragmentDirections.actionListDetailsFragmentToMainViewPagerFragment2()
            findNavController().navigate(action)
        }

        if (item.itemId == R.id.rename_item) {
            renameListWithDialog()
        }

        return super.onOptionsItemSelected(item)
    }

    private fun insertOrUpdateShoppingList(shoppingList: ShoppingList) {
        if (newList) {
            mViewModel.insertShoppingList(shoppingList)
        } else {
            mViewModel.updateShoppingList(shoppingList)
        }
    }

    override fun addDetail(detailName: String, detailQuantity: Double) {
        mViewModel.insertDetailForShoppingList(
            mShoppingList,
            ShoppingListDetail(detailName, detailQuantity)
        )
        listDetailsAdapter.notifyDataSetChanged()
    }

    override fun onItemClicked(shoppingListDetail: ShoppingListDetail) {
        mViewModel.updateDetail(shoppingListDetail)
    }

    override fun renameList(listName: String) {
        mShoppingList.name = listName
        insertOrUpdateShoppingList(mShoppingList)
        fragmentBinding.editTextName.text = listName
    }

    private fun renameListWithDialog() {
        val dialog = RenameDialog(this)
        dialog.show(requireActivity().supportFragmentManager, "renameListDialog")
    }
}