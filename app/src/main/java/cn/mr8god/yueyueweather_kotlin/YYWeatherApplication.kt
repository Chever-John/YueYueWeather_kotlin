package cn.mr8god.yueyueweather_kotlin

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.qweather.sdk.view.HeConfig

class YYWeatherApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val TOKEN = "TAkhjf8d1nlSlspN"
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}