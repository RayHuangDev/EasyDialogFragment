package com.ray.library.easydialogfragment.listener

import android.app.Dialog

/**
 * @author Ray Huang
 * @since 2020-01-02
 */
interface DialogListener {

    interface OnShowListener {
        /**
         * When dialog showing
         */
        fun onShow(tag: String?, dialog: Dialog)
    }

    interface OnDismissListener {
        /**
         * When dialog dismiss
         */
        fun onDismiss(tag: String?)
    }

    interface OnButtonClickListener {
        /**
         * On button positive click
         */
        fun onPositiveClick(tag: String?, dialog: Dialog)

        /**
         * On button negative click
         */
        fun onNegativeClick(tag: String?, dialog: Dialog)

        /**
         * On button neutral click
         */
        fun onNeutralClick(tag: String?, dialog: Dialog)
    }

    interface OnItemClickListener {
        /**
         * On dialog default items (using list_items) click
         */
        fun onItemClick(
            tag: String?,
            dialog: Dialog,
            title: String,
            which: Int
        ) // For dialog default items
    }
}