package cn.mr8god.yueyueweather_kotlin.logid.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.await
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object YYWeatherNetwork {
    // 首先使用ServiceCreator创建了一个PlaceService接口的动态代理对象
    private val placeService = ServiceCreator.create(PlaceService::class.java)

    /**
     * 然后定义了一个searchPlaces()函数，并在这里调用刚刚在PlaceService接口中定义的searchPlaces()方法
     * 以发起搜索城市数据请求为了让代码更加简洁，使用了技巧来简化Retrofit回调的写法，
     * 由于是需要借助于协程技术，因此之类又定义了一个await()函数
     * 当外部调用了YYWeatherNetwork的searchPlaces()函数的时候
     * Retrofit就会立即发起网络请求，同时当前的协程也会被阻塞住。直到服务器响应了我们的请求之后，
     * await()函数就会将解析出来的数据模型对象取出并返回，同时恢复当前协程的执行
     * 此外，searchPlaces()函数会将得到await()的返回值后会将数据再返回到上一层中
     * */
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()


    private suspend fun <T> Call<T>.await(): T{
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null")
                    )
                }
                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}