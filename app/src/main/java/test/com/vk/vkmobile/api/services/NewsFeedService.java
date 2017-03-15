package test.com.vk.vkmobile.api.services;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import test.com.vk.vkmobile.models.feed.NewsFeed;

/**
 * Created by yurak on 12.03.2017.
 */

public interface NewsFeedService {
    @GET("newsfeed.get")
    Call<NewsFeed> login(@Query("filters") String filters, @Query("count") int count,
                         @Query("access_token") String token, @Query("v") double version);

}
