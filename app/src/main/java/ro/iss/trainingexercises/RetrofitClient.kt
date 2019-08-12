package ro.iss.trainingexercises

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ro.iss.trainingexercises.base.utils.Utils
import java.util.concurrent.TimeUnit

object RetrofitClient {

    fun getQuizClient() : QuizMethods {
        val retrofitClient: Retrofit = configureQuizRetrofitClient()
        return retrofitClient.create(QuizMethods::class.java)
    }


    private fun configureQuizRetrofitClient() : Retrofit {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(Utils.QUIZ_BASE_URL)

        val okHttpClientBuilder = OkHttpClient.Builder()

        okHttpClientBuilder.readTimeout(Utils.BASE_READ_TIME, TimeUnit.MILLISECONDS)
        okHttpClientBuilder.connectTimeout(Utils.BASE_TIMEOUT_TIME, TimeUnit.MILLISECONDS)

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        okHttpClientBuilder.addInterceptor {
            val originalRequest = it.request()
            val requestBuilder = originalRequest.newBuilder()

            requestBuilder.addHeader("Content-Type", "application/json")
            requestBuilder.addHeader("X-IBM-Client-ID", "f18911a7-8147-40c4-bd87-26636326f8d1")
            requestBuilder.addHeader("X-IBM-Client-Secret", "E3nP5lG7dJ6pU8yX7iJ0kA1lW5fY7vH0jD3iX7mD8jU5oN0sN8")

            val response = it.proceed(requestBuilder.build())
            response
        }

        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor)

        retrofitBuilder.client(okHttpClientBuilder.build())

        return retrofitBuilder.build()
    }

}