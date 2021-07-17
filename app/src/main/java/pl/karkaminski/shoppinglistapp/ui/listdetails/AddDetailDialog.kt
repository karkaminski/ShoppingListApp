package pl.karkaminski.shoppinglistapp.ui.listdetails

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import pl.karkaminski.shoppinglistapp.R

class AddDetailDialog(private val addDetailDialogListener: AddDetailDialogListener) :
    DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_add_detail, null)
            val editTextName = view.findViewById<EditText>(R.id.edit_text_name)
            val editTextQuantity = view.findViewById<EditText>(R.id.edit_text_quantity)

                builder.setView(view)
                .setTitle(getString(R.string.add_product))
                .setPositiveButton(
                    getString(R.string.add_detail_save)
                ) { _, _ ->
                    addDetailDialogListener.addDetail(
                        editTextName.text.toString(),
                        editTextQuantity.text.toString().toDouble())
                }
                .setNegativeButton(
                    getString(R.string.add_detail_cancel)
                ) { _, _ ->
                    dialog?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    interface AddDetailDialogListener {
        fun addDetail(detailName: String, detailQuantity: Double)
    }
}