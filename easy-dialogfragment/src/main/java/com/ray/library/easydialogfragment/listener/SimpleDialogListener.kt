package com.ray.library.easydialogfragment.listener

import android.app.Dialog

/**
 * @author Ray Huang
 * @since 2020-01-02
 */
open class SimpleDialogListener: DialogListener.OnShowListener, DialogListener.OnDismissListener,
    DialogListener.OnButtonClickListener, DialogListener.OnItemClickListener{

    override fun onShow(tag: String?, dialog: Dialog) {
    }

    override fun onDismiss(tag: String?) {
    }

    override fun onPositiveClick(tag: String?, dialog: Dialog) {
    }

    override fun onNegativeClick(tag: String?, dialog: Dialog) {
    }

    override fun onNeutralClick(tag: String?, dialog: Dialog) {
    }

    override fun onItemClick(tag: String?, dialog: Dialog, title: String, which: Int) {
    }
}