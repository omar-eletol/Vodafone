package com.task.data.pref


object PrefImp {
    fun Pref.getToken(): String? = getValue(key = Pref.Token_KEY)
    fun Pref.setToken(token: String?) = setValue(key = Pref.Token_KEY, value = token)
}