package pl.karkaminski.shoppinglistapp.ui.dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import pl.karkaminski.shoppinglistapp.R
import java.lang.IllegalStateException

class RenameDialog(private val renameListDialogListener: RenameListDialogListener)
    : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater
            val view = inflater.inflate(R.layout.dialog_rename_list, null)
            val editText = view.findViewById<EditText>(R.id.edit_text_name)

            builder.setView(view)
                .setTitle(getString(R.string.set_list_name))
                .setPositiveButton( getString(R.string.save)) {
                    _, _ ->
                    renameListDialogListener.renameList(
                        editText.text.toString()
                    )
                }
                .setNegativeButton(getString(R.string.cancel)) {
                    _, _ ->
                    dialog?.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    interface RenameListDialogListener {
        fun renameList(listName: String)
    }
}