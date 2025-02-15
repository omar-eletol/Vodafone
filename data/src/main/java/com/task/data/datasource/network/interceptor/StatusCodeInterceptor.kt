package com.task.data.datasource.network.interceptor

import android.content.Context
import com.task.data.datasource.pref.Pref
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class StatusCodeInterceptor @Inject constructor(
    private val context: Context,
    private val pref: Pref
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val response: Response = chain.proceed(originalRequest)
        // remove user data if code is unAuthenticated (401)
        if (response.code == 401) removeUser()
        return response
    }

    private val _unAuth = Channel<Boolean>()
    val unAuth get() = _unAuth.receiveAsFlow()

    @OptIn(DelicateCoroutinesApi::class)
    private fun removeUser() = GlobalScope.launch {
//        pref.removeUserData()
//        context.launchEmptySessions()
    }
}