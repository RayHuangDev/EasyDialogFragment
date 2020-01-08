package com.ray.library.easydialogfragment

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.NonNull
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.ray.library.easydialogfragment.listener.SimpleDialogListener
import java.lang.Exception


/**
 * @author Ray Huang
 * @since 2020-01-02
 */
class EasyDialogFragment : DialogFragment() {

  companion object Factory {
    const val FIELD_THEME = "theme"
    const val FIELD_WINDOW_PROPERTY = "window.properties"
    const val FIELD_LAYOUT = "layout"
    const val FIELD_TITLE_RESOURCE = "title.res"
    const val FIELD_TITLE_STRING = "title.str"
    const val FIELD_MESSAGE_RESOURCE = "message.res"
    const val FIELD_MESSAGE_STRING = "message.str"
    const val FIELD_LIST_ITEMS = "list.items"
    const val FIELD_LIST_ITEMS_STRING = "list.items.string"
    const val FIELD_LABEL_POSITIVE_RESOURCE = "label.positive.res"
    const val FIELD_LABEL_POSITIVE_STRING = "label.positive.str"
    const val FIELD_LABEL_NEGATIVE_RESOURCE = "label.negative.res"
    const val FIELD_LABEL_NEGATIVE_STRING = "label.negative.str"
    const val FIELD_LABEL_NEUTRAL_RESOURCE = "label.neutral.res"
    const val FIELD_LABEL_NEUTRAL_STRING = "label.neutral.str"
    const val FIELD_TEXT_SIZE = "text.size"
  }

  private lateinit var alertDialog: AlertDialog
  private lateinit var simpleDialogListener: SimpleDialogListener

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    val bundle = arguments
    /* theme field */
    val theme: Int = if (bundle!!.containsKey(FIELD_THEME)) {
      bundle.getInt(FIELD_THEME)
    } else {
      R.style.easy_dialog_normal_style
    }
    /* dialog builder */
    val builder = activity?.let {
      AlertDialog.Builder(
        it,
        theme
      )
    }
    /* layout field */
    if (bundle.containsKey(FIELD_LAYOUT)) {
      val layoutInflater: LayoutInflater =
        activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
      if (layoutInflater != null) {
        val content = layoutInflater.inflate(
          bundle.getInt(FIELD_LAYOUT),
          null
        )
        builder?.setView(content)
      }
    }
    /* title field */
    if (bundle.containsKey(FIELD_TITLE_RESOURCE)) {
      builder!!.setTitle(bundle.getInt(FIELD_TITLE_RESOURCE))
    } else if (bundle.containsKey(FIELD_TITLE_STRING)) {
      builder!!.setTitle(bundle.getString(FIELD_TITLE_STRING))
    }
    /* message field */
    if (bundle.containsKey(FIELD_MESSAGE_RESOURCE)) {
      builder!!.setMessage(bundle.getInt(FIELD_MESSAGE_RESOURCE))
    } else if (bundle.containsKey(FIELD_MESSAGE_STRING)) {
      builder!!.setMessage(bundle.getString(FIELD_MESSAGE_STRING))
    }
    /* items field */
    val items = ArrayList<String>()
    if (bundle.containsKey(FIELD_LIST_ITEMS)) {
      val listItems: IntArray? = bundle.getIntArray(FIELD_LIST_ITEMS)
      if (listItems != null) {
        for (item: Int in listItems) {
          items.add(getString(item))
        }
      }
    }
    /* string items field */
    if (bundle.containsKey(FIELD_LIST_ITEMS_STRING)) {
      val listItems = bundle.getStringArrayList(FIELD_LIST_ITEMS_STRING)
      if (listItems != null) {
        items.addAll(listItems)
      }
    }

    if (items.size > 0) {
      // if u want set onClickListener
      /*builder?.setItems(items.toTypedArray(), object : DialogInterface.OnClickListener() {
          override fun onClick(dialog: DialogInterface?, which: Int) {
              if (simpleDialogListener != null) {
                  simpleDialogListener.onItemClick(tag, alertDialog, items[which], which)
              }
          }
      })*/
      builder?.setItems(
        items.toTypedArray(),
        null
      )
    }
    /* positive button filed */
    if (bundle.containsKey(FIELD_LABEL_POSITIVE_RESOURCE)) {
      builder?.setPositiveButton(
        bundle.getString(FIELD_LABEL_POSITIVE_RESOURCE)
      ) { _, _ ->
        // this will never be called, if we set View.onClickListener
      }
    } else if (bundle.containsKey(FIELD_LABEL_POSITIVE_STRING)) {
      builder!!.setPositiveButton(
        bundle.getString(FIELD_LABEL_POSITIVE_STRING)
      ) { _, _ ->
        // this will never be called, if we set View.onClickListener
      }
    }
    /* negative button field */
    if (bundle.containsKey(FIELD_LABEL_NEGATIVE_RESOURCE)) {
      builder!!.setNegativeButton(
        bundle.getInt(FIELD_LABEL_NEGATIVE_RESOURCE)
      ) { _, _ ->
        // this will never be called, if we set View.onClickListener
      }
    } else if (bundle.containsKey(FIELD_LABEL_NEGATIVE_STRING)) {
      builder!!.setNegativeButton(
        bundle.getString(FIELD_LABEL_NEGATIVE_STRING)
      ) { _, _ ->
        // this will never be called, if we set View.onClickListener
      }
    }
    /* neutral button field */
    if (bundle.containsKey(FIELD_LABEL_NEUTRAL_RESOURCE)) {
      builder!!.setNeutralButton(
        bundle.getInt(FIELD_LABEL_NEUTRAL_RESOURCE)
      ) { _, _ ->
        // this will never be called, if we set View.onClickListener
      }
    } else if (bundle.containsKey(FIELD_LABEL_NEUTRAL_STRING)) {
      builder!!.setNeutralButton(
        bundle.getString(FIELD_LABEL_NEUTRAL_STRING)
      ) { _, _ ->
        // this will never be called, if we set View.onClickListener
      }
    }

    alertDialog = builder!!.create()
    return alertDialog
  }

  @NonNull
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return super.onCreateView(
      inflater,
      container,
      savedInstanceState
    )
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    // Registers View.onClickListener is to interrupt event from original listener
    if (simpleDialogListener != null) {
      alertDialog.setOnShowListener {
        val bundle = arguments
        val dimenId = bundle!!.getInt(
          FIELD_TEXT_SIZE,
          R.dimen.easy_dialog_small_text_size
        )
        configureTitle(dimenId)
        configurePositiveButton(dimenId)
        configureNegativeButton(dimenId)
        configureNeutralButton(dimenId)
        simpleDialogListener.onShow(
          tag,
          alertDialog
        )
      }
    }
  }

  override fun onDismiss(dialog: DialogInterface?) {
    super.onDismiss(dialog)

    if (simpleDialogListener != null) {
      simpleDialogListener.onDismiss(tag)
    }
  }

  /**
   * Sets custom dialog listener to received call back from [SimpleDialogListener]
   */
  fun setListener(listener: SimpleDialogListener) {
    this.simpleDialogListener = listener
  }

  /* private function */

  private fun buildDialogWindow() {
    val window = dialog.window ?: return
    /* input mode */window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
    /* window property */
    val bundle = arguments
    val property: DialogWindowProperty = bundle!!.getParcelable(FIELD_WINDOW_PROPERTY)
      ?: return
    val animation: Int = property.getAnimation()
    if (animation != -1) {
      window.setWindowAnimations(animation)
    }
    window.setGravity(property.getGravity())
    val wl = window.attributes
    wl.width = property.getWidth()
    wl.height = property.getHeight()
    wl.x += property.getDeltaX()
    wl.y += property.getDeltaY()
    window.attributes = wl
    val backgroundResId: Int = property.getBackgroundResId()
    val backgroundColor: Int = property.getBackgroundColor()
    if (backgroundResId != -1) {
      window.setBackgroundDrawable(resources.getDrawable(backgroundResId))
    } else if (backgroundColor != -1) {
      window.setBackgroundDrawable(ColorDrawable(backgroundColor))
    }
  }

  private fun configureTitle(dimenId: Int) {
    val titleDividerId = resources.getIdentifier(
      "titleDivider",
      "id",
      "android"
    )
    val titleDivider: View? = alertDialog.findViewById(titleDividerId)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      // fixme, not sure theme null ok or not
      titleDivider?.setBackgroundColor(
        resources.getColor(
          android.R.color.white,
          null
        )
      )
    } else {
      titleDivider?.setBackgroundColor(
        resources.getColor(
          android.R.color.white
        )
      )
    }
  }

  private fun configurePositiveButton(dimenId: Int) {
    val positiveBtn = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
    positiveBtn.setTypeface(
      Typeface.DEFAULT,
      Typeface.BOLD
    )
    positiveBtn.setTextSize(
      TypedValue.COMPLEX_UNIT_PX,
      resources.getDimensionPixelSize(dimenId).toFloat()
    )
    positiveBtn.setOnClickListener {
      if (simpleDialogListener != null) {
        simpleDialogListener.onPositiveClick(
          tag,
          alertDialog
        )
      }
    }
  }

  private fun configureNegativeButton(dimenId: Int) {
    val negativeBtn = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
    negativeBtn.setTypeface(
      Typeface.DEFAULT,
      Typeface.BOLD
    )
    negativeBtn.setTextSize(
      TypedValue.COMPLEX_UNIT_PX,
      resources.getDimensionPixelSize(dimenId).toFloat()
    )
    negativeBtn.setOnClickListener {
      if (simpleDialogListener != null) {
        simpleDialogListener.onNegativeClick(
          tag,
          alertDialog
        )
      }
    }
  }

  private fun configureNeutralButton(dimenId: Int) {
    val neutralBtn = alertDialog.getButton(DialogInterface.BUTTON_NEUTRAL)
    neutralBtn.setTypeface(
      Typeface.DEFAULT,
      Typeface.BOLD
    )
    neutralBtn.setTextSize(
      TypedValue.COMPLEX_UNIT_PX,
      resources.getDimensionPixelSize(dimenId).toFloat()
    )
    neutralBtn.setOnClickListener {
      if (simpleDialogListener != null) {
        simpleDialogListener.onNeutralClick(
          tag,
          alertDialog
        )
      }
    }
  }
}