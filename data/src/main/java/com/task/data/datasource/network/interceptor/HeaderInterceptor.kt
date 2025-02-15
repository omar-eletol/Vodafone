package com.task.data.datasource.network.interceptor

import com.task.data.datasource.pref.Pref
import com.task.data.datasource.pref.PrefImp.getToken
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class HeaderInterceptor @Inject constructor(private val pref: Pref) : Interceptor {
    private val token: String? get() = pref.getToken()


    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request.Builder = chain.request().newBuilder().apply {
            addHeader("Accept", "application/json")
            addHeader("Content-Type", "application/x-www-form-urlencoded")
            token?.let { addHeader("Authorization", "Bearer $it") }
        }
        return chain.proceed(request.build())
    }
}