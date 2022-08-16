package com.bivizul.howtobetonsportsforbeginners11tips.data

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import com.bivizul.howtobetonsportsforbeginners11tips.R
import java.util.*

fun getSetRes(context: Context): Locale = context.resources.configuration.locales[0]

fun checkNetwork(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

fun getError(context: Context, activity: Activity) {
    AlertDialog.Builder(context).apply {
        setTitle(context.getString(R.string.oops_error))
        setMessage(context.getString(R.string.error_message))

        setPositiveButton(context.getString(R.string.exit)) { _, _ ->
            activity.finishAndRemoveTask()
            System.exit(0)
        }
        setCancelable(true)
    }.create().show()
}
