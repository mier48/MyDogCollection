package com.albertomier.mydogcollection.core

import android.app.AlertDialog
import android.content.Context
import android.util.Patterns

object Utils {

    fun isEmailValid(email: String?): Boolean {
        return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun showErrorDialog(titleId: Int, messageId: Int, context: Context) {
        AlertDialog.Builder(context)
            .setTitle(titleId)
            .setMessage(messageId)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                //dissmiss
            }
            .create()
            .show()
    }
}