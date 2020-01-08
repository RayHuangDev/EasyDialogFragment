package com.ray.easydialogfragment

import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ray.library.easydialogfragment.DialogWindowProperty
import com.ray.library.easydialogfragment.EasyDialog
import com.ray.library.easydialogfragment.listener.SimpleDialogListener

class DemoActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_demo)

    EasyDialog.Fatory.Builder()
      .setTitle("TEST")
      //.setMessage("FAST and EASY.")
      .setLayout(R.layout.dialog_verify)
      //.setPositiveButtonLabel("OK")
      //.setNegativeButtonLabel("CANCEL")
      //.setNeutralButtonLabel("SKIP")
      .setSimpleDialogListener(object : SimpleDialogListener() {
        override fun onShow(
          tag: String?,
          dialog: Dialog
        ) {
          super.onShow(
            tag,
            dialog
          )
          dialog.run {
            findViewById<View>(R.id.verify_register_btn).setOnClickListener {
              Log.e(
                "yoyo",
                "verify click"
              )
              this.dismiss()
            }
          }
        }

        override fun onDismiss(tag: String?) {
          super.onDismiss(tag)
          Log.e(
            "yoyo",
            "onDismiss"
          )
        }

        override fun onPositiveClick(
          tag: String?,
          dialog: Dialog
        ) {
          super.onPositiveClick(
            tag,
            dialog
          )
          Log.e(
            "yoyo",
            "onPositiveClick"
          )
          dialog!!.dismiss()
        }

        override fun onNegativeClick(
          tag: String?,
          dialog: Dialog
        ) {
          super.onNegativeClick(
            tag,
            dialog
          )
          Log.e(
            "yoyo",
            "onNegativeClick"
          )
        }
      })
      .setDialogWindowProperty(
        DialogWindowProperty.Factory.Builder().setGravity(Gravity.BOTTOM)
          .setBackgroundColor(Color.TRANSPARENT)
          .build()
      )
      .build(
        supportFragmentManager,
        "TEST"
      )
  }


}
