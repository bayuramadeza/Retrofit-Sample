package teamd.binar.com.retrofit;

import android.app.Application;
import android.os.Build;

import java.util.logging.LoggingMXBean;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HTTP;
import teamd.binar.com.retrofit.activity.Services;

/**
 * Created by bayuramadeza on 4/3/2018.
 */

public class MyNewsApp extends Application {
    public static Services services;
    @Override
    public void onCreate() {
        super.onCreate();

        services = getRetrofit().create(Services.class);

    }

    private Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getHttpClient())
                .build();
    }

    private OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(getHttpLogInterceptor())
                .build();
    }

    private Interceptor getHttpLogInterceptor() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        HttpLoggingInterceptor.Level level;

        if (BuildConfig.DEBUG){
            level = HttpLoggingInterceptor.Level.BODY;
        } else {
            level = HttpLoggingInterceptor.Level.NONE;
        }
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }
}
