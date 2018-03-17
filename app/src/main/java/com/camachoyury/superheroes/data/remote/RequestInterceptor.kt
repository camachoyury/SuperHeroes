package com.camachoyury.superheroes.data.remote

import com.camachoyury.superheroes.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

import java.util.*
import android.provider.SyncStateContract.Helpers.update
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

/**
 * Created by Yury Camacho on 14/02/2018.
 */
class RequestInterceptor(): Interceptor {

    var PUBLIC_KEY = BuildConfig.PUBLIC_API_KEY
    var PRIVATE_KEY = BuildConfig.PRIVATE_API_KEY


    override fun intercept(chain: Interceptor.Chain?): Response {
        val original = chain!!.request()
        var originalHttpUrl =  original.url()

        val now = Date()
        val timestamp = now.time.toString()
        var url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey",PUBLIC_KEY)
                .addQueryParameter("ts",timestamp)
                .addQueryParameter("hash", hash(PUBLIC_KEY, PRIVATE_KEY,timestamp   ) )
                .build()

        // Request customization: add request headers
        val requestBuilder = original.newBuilder()
                .url(url)
        var request = requestBuilder.build()

        return chain.proceed(request)

    }


    fun md5(s: String): String {
        try {
            // Create MD5 Hash
            val digest = java.security.MessageDigest.getInstance("MD5")
            digest.update(s.toByteArray())
            val messageDigest = digest.digest()

            // Create Hex String
            val hexString = StringBuffer()
            for (i in messageDigest.indices)
                hexString.append(Integer.toHexString(0xFF and messageDigest[i].toInt()))

            return hexString.toString()
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }

        return ""
    }


        fun hash(publicApiKey: String, privateApiKey: String, timestamp: String): String? {
            try {
                val md = MessageDigest.getInstance("MD5")
                val toHash = timestamp + privateApiKey + publicApiKey
                return BigInteger(1, md.digest(toHash.toByteArray())).toString(16)
            } catch (e: NoSuchAlgorithmException) {
            }

            return null

    }

//
}