package cn.mr8god.yueyueweather_kotlin.logid.network

import cn.mr8god.yueyueweather_kotlin.YYWeatherApplication
import cn.mr8god.yueyueweather_kotlin.annontaion.GET
import cn.mr8god.yueyueweather_kotlin.logid.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.Query

interface PlaceService {
    // 城市信息搜索，获取城市id用于天气查询呢
    @GET("v2/place?token=${YYWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}