package com.ray.library.easydialogfragment

import android.os.Bundle
import androidx.annotation.DimenRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import com.ray.library.easydialogfragment.EasyDialogFragment.Factory.FIELD_LABEL_NEGATIVE_RESOURCE
import com.ray.library.easydialogfragment.EasyDialogFragment.Factory.FIELD_LABEL_NEGATIVE_STRING
import com.ray.library.easydialogfragment.EasyDialogFragment.Factory.FIELD_LABEL_NEUTRAL_RESOURCE
import com.ray.library.easydialogfragment.EasyDialogFragment.Factory.FIELD_LABEL_NEUTRAL_STRING
import com.ray.library.easydialogfragment.EasyDialogFragment.Factory.FIELD_LABEL_POSITIVE_RESOURCE
import com.ray.library.easydialogfragment.EasyDialogFragment.Factory.FIELD_LABEL_POSITIVE_STRING
import com.ray.library.easydialogfragment.EasyDialogFragment.Factory.FIELD_LAYOUT
import com.ray.library.easydialogfragment.EasyDialogFragment.Factory.FIELD_LIST_ITEMS_STRING
import com.ray.library.easydialogfragment.EasyDialogFragment.Factory.FIELD_MESSAGE_RESOURCE
import com.ray.library.easydialogfragment.EasyDialogFragment.Factory.FIELD_MESSAGE_STRING
import com.ray.library.easydialogfragment.EasyDialogFragment.Factory.FIELD_TEXT_SIZE
import com.ray.library.easydialogfragment.EasyDialogFragment.Factory.FIELD_TITLE_RESOURCE
import com.ray.library.easydialogfragment.EasyDialogFragment.Factory.FIELD_TITLE_STRING
import com.ray.library.easydialogfragment.listener.SimpleDialogListener
import java.util.*
import kotlin.LazyThreadSafetyMode.NONE

/**
 * @author Ray Huang
 * @since 2020-01-02
 */
class EasyDialog private constructor() {

  init {
    throw UnsupportedOperationException()
  }

  companion object Fatory {

    private val TAG = "EasyDialog"

    private val fragment: EasyDialogFragment by lazy(NONE)
    {
      EasyDialogFragment()
    }
    private val bundle: Bundle by lazy(NONE)
    {
      Bundle()
    }
    private lateinit var dialogWindowProperty: DialogWindowProperty

    class Builder {

      /**
       * Sets layout resource
       */
      fun setLayout(@LayoutRes layoutId: Int): Builder {
        bundle.putInt(
          FIELD_LAYOUT,
          layoutId
        )
        return this
      }

      /**
       * Sets dialog title by resource
       */
      fun setTitleId(@StringRes titleId: Int): Builder {
        bundle.putInt(
          FIELD_TITLE_RESOURCE,
          titleId
        )
        return this
      }

      /**
       * Sets dialog title by string
       */
      fun setTitle(title: String): Builder {
        bundle.putString(
          FIELD_TITLE_STRING,
          title
        )
        return this
      }

      /**
       * Sets dialog message by resource
       */
      fun setMessage(@StringRes stringId: Int): Builder {
        bundle.putInt(
          FIELD_MESSAGE_RESOURCE,
          stringId
        )
        return this
      }

      /**
       * Sets dialog message by string
       */
      fun setMessage(string: String?): Builder {
        bundle.putString(
          FIELD_MESSAGE_STRING,
          string
        )
        return this
      }

      /**
       * Sets dialog message by String list
       */
      fun setMessage(strings: ArrayList<String?>?): Builder {
        bundle.putStringArrayList(
          FIELD_LIST_ITEMS_STRING,
          strings
        )
        return this
      }

      /**
       * Sets positive button label by resource
       */
      fun setPositiveButtonLabel(@StringRes labelId: Int): Builder {
        bundle.putInt(
          FIELD_LABEL_POSITIVE_RESOURCE,
          labelId
        )
        return this
      }

      /**
       * Sets positive button label by string
       */
      fun setPositiveButtonLabel(label: String?): Builder {
        bundle.putString(
          FIELD_LABEL_POSITIVE_STRING,
          label
        )
        return this
      }

      /**
       * Sets negative button label by resource id
       */
      fun setNegativeButtonLabel(@StringRes labelId: Int): Builder {
        bundle.putInt(
          FIELD_LABEL_NEGATIVE_RESOURCE,
          labelId
        )
        return this
      }

      /**
       * Sets negative button label by string
       */
      fun setNegativeButtonLabel(label: String?): Builder {
        bundle.putString(
          FIELD_LABEL_NEGATIVE_STRING,
          label
        )
        return this
      }

      /**
       * Sets neutral button label by resource id
       */
      fun setNeutralButtonLabel(@StringRes labelId: Int): Builder {
        bundle.putInt(
          FIELD_LABEL_NEUTRAL_RESOURCE,
          labelId
        )
        return this
      }

      /**
       * Sets neutral button label by string
       */
      fun setNeutralButtonLabel(label: String?): Builder {
        bundle.putString(
          FIELD_LABEL_NEUTRAL_STRING,
          label
        )
        return this
      }

      /**
       * Sets text size resource
       */
      fun setTextSizeResId(@DimenRes sizeResId: Int): Builder {
        bundle.putInt(
          FIELD_TEXT_SIZE,
          sizeResId
        )
        return this
      }

      fun setDialogWindowProperty(dialogWindowProperty: DialogWindowProperty): Builder {
        Fatory.dialogWindowProperty = dialogWindowProperty
        return this
      }

      /**
       * Sets dialog is cancelable or not
       *
       * @param cancelable true cancelable, false not
       */
      fun setCancelable(cancelable: Boolean): Builder {
        fragment.setCancelable(cancelable)
        return this
      }

      /**
       * Sets simple dialog listener
       *
       * @param listener simple dialog listener
       */
      fun setSimpleDialogListener(listener: SimpleDialogListener): Builder {
        fragment.setListener(listener)
        return this
      }

      /**
       * Build dialog fragment
       */
      fun build(fragmentManager: FragmentManager) {
        build(
          fragmentManager,
          Fatory.TAG
        )
      }

      /**
       * Build dialog fragment
       */
      fun build(
        fragmentManager: FragmentManager,
        tag: String?
      ) {
        if (dialogWindowProperty == null) {
          dialogWindowProperty = DialogWindowProperty.Factory.Builder().build()
        }
        /* put property */bundle.putParcelable(
          EasyDialogFragment.FIELD_WINDOW_PROPERTY,
          dialogWindowProperty
        )
        fragment.arguments = bundle
        fragment.show(
          fragmentManager,
          tag
        )
      }
    }
  }
}