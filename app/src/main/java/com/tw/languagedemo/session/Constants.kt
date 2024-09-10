package com.tw.languagedemo.session

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.location.Address
import android.location.Geocoder
import android.media.MediaMetadataRetriever
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.provider.ContactsContract
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import com.tw.languagedemo.R
import java.io.ByteArrayOutputStream
import java.util.*


object Constants {

    var TAG:String = this.javaClass.simpleName


    fun getMyUUID(context: Context):String{
        var mId:String
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID).also { mId = it }
        println("Android UDID:: "+mId)
        return mId
    }

    fun removeSymbol(context: Context, string: String):String{
        return string.replace("*", "")
    }


    fun showAlertForValidation(context: Context, msg: String) {
        val builder = AlertDialog.Builder(context)
        builder.setCancelable(false)
        builder.setTitle(context.resources.getString(R.string.app_name))
        builder.setMessage(msg)
        // builder.setInverseBackgroundForced(true)
        builder.setPositiveButton(context.resources.getString(R.string._ok)) { dialog: DialogInterface?, which: Int ->
            if(dialog != null) {
                dialog.dismiss()
            }
        }
        val alert = builder.create()
        alert.show()
    }


    fun applyLanguage(context: Context, lang: String) {
        val locale = Locale(lang)
        Locale.setDefault(locale)
        val config = context.resources.configuration
        config.setLocale(locale)
        context.createConfigurationContext(config)
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
    }

    fun setAppLocale(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources: Resources = context.resources
        val config = resources.configuration
        config.setLocale(locale)
        context.createConfigurationContext(config)
        resources.updateConfiguration(config, resources.displayMetrics)

    }

}