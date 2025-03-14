package com.madslangkilde.repository_base_server

import okhttp3.Interceptor
import okhttp3.Response
import org.koin.core.component.KoinComponent

class ServerRequestInterceptor : Interceptor, KoinComponent {

    private var requestTag: String? = null
    private var extraParams: Map<String, String> = emptyMap()

    class Builder {
        private var interceptor: ServerRequestInterceptor = ServerRequestInterceptor()

        fun setRequestTag(tag: String) {
            interceptor.requestTag = tag
        }

        fun setExtraParams(params: Map<String, String>) {
            interceptor.extraParams = params
        }

        fun build(): ServerRequestInterceptor = interceptor
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        requestBuilder.tag(requestTag)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}