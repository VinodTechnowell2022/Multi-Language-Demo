package com.tw.languagedemo.ui

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Html
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.tw.languagedemo.R
import com.tw.languagedemo.databinding.ActivityMainBinding
import com.tw.languagedemo.session.Constants
import com.tw.languagedemo.session.SessionManager
import com.tw.languagedemo.session.SessionVar


class ChangeLanguage : AppCompatActivity(), LanguageAdapter.OnItemClick {


    private lateinit var binding: ActivityMainBinding
    private lateinit var sessionManager: SessionManager
    private var TAG: String = this.javaClass.simpleName
    private var selectedLanguage: String = ""

    private lateinit var mAdapter: LanguageAdapter
    private var languageList : MutableList<LanguageData> = mutableListOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)
        selectedLanguage = sessionManager.getSelectedLanguage()[SessionVar.KEY_SELECT_LANGUAGE].toString()
        Log.e(TAG, "selectedLanguage : $selectedLanguage" )
        Constants.setAppLocale(this@ChangeLanguage, sessionManager.getSelectedLanguage()[SessionVar.KEY_SELECT_LANGUAGE].toString())
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        getLanguageList()
    }



    private fun getLanguageList() {
        if (selectedLanguage.equals("en", ignoreCase = true)) {
            languageList.add(LanguageData(1, resources.getString(R.string.english) + " ( अंग्रेज़ी )", "en"))
            languageList.add(LanguageData(2, resources.getString(R.string.tamil), "ta"))
            languageList.add(LanguageData(3, resources.getString(R.string.telgu), "te"))
            languageList.add(LanguageData(4, resources.getString(R.string.hindi) + " ( हिंदी )", "hi"))
        } else {
            languageList.add(LanguageData(1, resources.getString(R.string.english) + " ( English )", "en") )
            languageList.add(LanguageData(2, resources.getString(R.string.tamil) + " (Tamil)", "ta"))
            languageList.add(LanguageData(3, resources.getString(R.string.telgu) +" (Telugu)", "te"))
            languageList.add(LanguageData(4, resources.getString(R.string.hindi)+" ( Hindi )", "hi"))
        }


        if (languageList.size>0){
            binding.recyclerView.layoutManager= LinearLayoutManager(this@ChangeLanguage, LinearLayoutManager.VERTICAL, false)
            binding.recyclerView.isNestedScrollingEnabled=false
            binding.recyclerView.setHasFixedSize(true)

            mAdapter = LanguageAdapter(languageList)
            binding.recyclerView.adapter = mAdapter

            mAdapter.onItemClick = { model ->
                // do something with your item
                Log.e(this.javaClass.simpleName, "onItemClicked  " + model.name)

                changeLanguageAlert(this, model)
            }
        }
    }


    data class LanguageData(val id: Int, val name:String, val langCode:String)

    override fun onItemClicked(position: Int, view: View) {
        Log.e(TAG, "onItemClicked: "+languageList.get(position).name )
    }



    private fun changeLanguageAlert(context: Activity, model: LanguageData){
        val res = resources
        val message = res.getString(R.string.language_change_alert)
        val text = "<font color=#e36009>$message</font> <font color=#5502C8>${model.name.plus(" Language !!")}</font>"

        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.app_name))
        builder.setIcon(ContextCompat.getDrawable(context, R.mipmap.ic_launcher))
        builder.setMessage(Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY))
        builder.setCancelable(false)

        builder.setPositiveButton(resources.getString(R.string._ok)) { dialog, _ ->

            sessionManager.setSelectedLanguage(model.langCode)
            Log.e(TAG, "changed language: "+sessionManager.getSelectedLanguage()[SessionVar.KEY_SELECT_LANGUAGE].toString() )

            Constants.setAppLocale(this@ChangeLanguage, sessionManager.getSelectedLanguage()[SessionVar.KEY_SELECT_LANGUAGE].toString())

            refreshTheScreen()

            dialog.dismiss()
        }
        builder.setNegativeButton(resources.getString(R.string._cancel)) { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    private fun refreshTheScreen() {
        val i = Intent(this@ChangeLanguage, HomeScreen::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(OVERRIDE_TRANSITION_OPEN,0, 0)
            startActivity(i)
            overrideActivityTransition(OVERRIDE_TRANSITION_OPEN,0, 0)
        }else{
            overridePendingTransition(0, 0)
            startActivity(i)
            overridePendingTransition(0, 0)
        }

    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            enableEdgeToEdge()
        }
    }


}