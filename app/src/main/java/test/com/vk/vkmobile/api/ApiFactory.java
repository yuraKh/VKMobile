package test.com.vk.vkmobile.api;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import test.com.vk.vkmobile.api.services.NewsFeedService;

/**
 * Created by yurak on 12.03.2017.
 */

public class ApiFactory {

    private static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    private static OkHttpClient client;// = new OkHttpClient();
    private static OkHttpClient.Builder builder;// = new OkHttpClient.Builder();
    private static HttpLoggingInterceptor interceptor;// = new HttpLoggingInterceptor();


    public static void init() {
        interceptor = new HttpLoggingInterceptor();
        builder = new OkHttpClient.Builder();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        client = builder.build();
    }

    @NonNull
    public static NewsFeedService getNewsFeedService() {
        return getRetrofit().create(NewsFeedService.class);
    }


    @NonNull
    private static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.vk.com/method/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }
}
