package com.h4hacks.sslpinning.h4hacks_webview

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class DomainWhitelistInterceptor(private val whitelistedDomains: Array<String>): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d(javaClass.simpleName, "intercepted ${chain.request().url}")
        val currentDomain = chain.request().url.host

        whitelistedDomains.forEach {
            if(currentDomain.endsWith(it)) {
                return chain.proceed(chain.request())
            }
        }
        throw IOException("Domain $currentDomain not whitelisted")
    }
}
