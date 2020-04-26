package com.h4hacks.sslpinning.h4hacks_webview

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class SchemeWhitelistInterceptor(private val whitelistedSchemes: Array<String>): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d(javaClass.simpleName, "intercepted ${chain.request().url}")

        val currentScheme = chain.request().url.scheme

        whitelistedSchemes.forEach {
            if(currentScheme == it) {
                return chain.proceed(chain.request())
            }
        }
        throw IOException("Scheme $currentScheme not whitelisted")
    }
}
