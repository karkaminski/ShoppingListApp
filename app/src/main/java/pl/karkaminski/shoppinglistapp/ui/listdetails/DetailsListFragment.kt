package pl.karkaminski.shoppinglistapp.ui.listdetails

import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import pl.karkaminski.shoppinglistapp.R
import pl.karkaminski.shoppinglistapp.data.ShoppingList
import pl.karkaminski.shoppinglistapp.data.ShoppingListDetail
import pl.karkaminski.shoppinglistapp.databinding.ListDetailsFragmentBinding
import pl.karkaminski.shoppinglistapp.ui.ShoppingListsViewModel

class DetailsListFragment : Fragment(), AddDetailDialog.AddDetailDialogListener,
DetailsListAdapter.OnItemClickedListener{

    private lateinit var mViewModel: ShoppingListsViewModel

    private var mShoppingList: ShoppingList? = null

    private val args by navArgs<DetailsListFragmentArgs>()
    private val listDetailsAdapter = DetailsListAdapter(this)
    private var newList = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val fragmentBinding = ListDetailsFragmentBinding.inflate(inflater, container, false)

        mViewModel = ViewModelProvider(this).get(ShoppingListsViewModel::class.java)
        mShoppingList = args.shoppingList

        fragmentBinding.editTextName.setText(mShoppingList?.name)
        fragmentBinding.recyclerView.adapter = listDetailsAdapter

        if (mShoppingList == null) {
            mShoppingList = ShoppingList("")
        }

        mViewModel.getAllDetailsForShoppingList(mShoppingList!!).observe(
            viewLifecycleOwner
        ) { list ->
            listDetailsAdapter.apply {
                if (list != null) {
                    detailsList = list
                    notifyDataSetChanged()
                }
            }
        }

        fragmentBinding.editTextName.setOnFocusChangeListener { viewEditTextName, hasFocus ->
            if (!hasFocus) {
                mShoppingList!!.name = (viewEditTextName as EditText).text.toString()
                insertOrUpdate(mShoppingList!!)
            }
        }

        fragmentBinding.floatingActionButton.setOnClickListener {
            fragmentBinding.editTextName.clearFocus()

            val dialog = AddDetailDialog(this)
            dialog.show(requireActivity().supportFragmentManager, "addDetailDialog")
        }

        return fragmentBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.list_details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.archive_list_item){
            mShoppingList!!.isActive = false
            mViewModel.updateShoppingList(mShoppingList!!)
            requireActivity().supportFragmentManager.popBackStack()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insertOrUpdate(shoppingList: ShoppingList) {
        if (newList) {
            mViewModel.insertShoppingList(shoppingList)
        } else {
            mViewModel.updateShoppingList(shoppingList)
        }
    }

    override fun addDetail(detailName: String, detailQuantity: Double) {
        mViewModel.insertDetailForShoppingList(
            mShoppingList!!,
            ShoppingListDetail(detailName, detailQuantity)
        )
        listDetailsAdapter.notifyDataSetChanged()
    }

    override fun onItemClicked(shoppingListDetail: ShoppingListDetail) {
        mViewModel.updateDetail(shoppingListDetail)
    }
}