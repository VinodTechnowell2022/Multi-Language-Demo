package com.tw.languagedemo.session

import android.content.Context
import android.content.SharedPreferences

object SessionVar {
    var ISLOGIN = "islogin"
    const val KEY_SELECT_LANGUAGE = "key_select_language"
}

private const val PREF_NAME = "lang_session"

class SessionManager(context: Context) {

    private val appContext = context.applicationContext
    private val preference: SharedPreferences = appContext.getSharedPreferences(PREF_NAME, 0)   //0 for private mode
    private val editor: SharedPreferences.Editor = preference.edit()

    fun setSelectedLanguage(language: String) {
        editor.putString(SessionVar.KEY_SELECT_LANGUAGE, language).apply()
    }

    fun getSelectedLanguage(): HashMap<String, String> {
        val language = HashMap<String, String>()
        language.put(SessionVar.KEY_SELECT_LANGUAGE, preference.getString(SessionVar.KEY_SELECT_LANGUAGE, "en").toString())
        return language
    }

}