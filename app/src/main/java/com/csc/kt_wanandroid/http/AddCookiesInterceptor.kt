package com.csc.kt_wanandroid.http

import com.csc.core.manage.AccountManager
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.util.*

class AddCookiesInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        var cookies = HashSet<String>()
        if (AccountManager.getCookie() != null) {
            cookies = AccountManager.getCookie() as HashSet<String>
        }
        if (cookies != null) {
            for (cookie in cookies) {
                builder.addHeader("Cookie", cookie)
            }
        }
        return chain.proceed(builder.build())
    }
}