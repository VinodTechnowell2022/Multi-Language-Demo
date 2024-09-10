package com.tw.languagedemo.ui

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tw.languagedemo.R
import com.tw.languagedemo.databinding.ActivityMainBinding
import com.tw.languagedemo.databinding.HomeScreenBinding
import com.tw.languagedemo.session.Constants
import com.tw.languagedemo.session.SessionManager
import com.tw.languagedemo.session.SessionVar

class HomeScreen : AppCompatActivity() {

    private lateinit var binding: HomeScreenBinding
    private lateinit var sessionManager: SessionManager
    private var TAG: String = this.javaClass.simpleName
    private var selectedLanguage: String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sessionManager = SessionManager(this)
        selectedLanguage = sessionManager.getSelectedLanguage()[SessionVar.KEY_SELECT_LANGUAGE].toString()
        Log.e(TAG, "selectedLanguage : $selectedLanguage" )
        Constants.setAppLocale(this@HomeScreen, sessionManager.getSelectedLanguage()[SessionVar.KEY_SELECT_LANGUAGE].toString())
        binding = HomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnChangeLanguage.setOnClickListener {

            startActivity(android.content.Intent(this@HomeScreen, ChangeLanguage::class.java))
        }

    }



    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            enableEdgeToEdge()
        }
    }
}