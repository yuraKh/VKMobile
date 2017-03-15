package test.com.vk.vkmobile.loader;

import android.content.Context;
import android.util.Log;

import com.vk.sdk.VKAccessToken;

import java.io.IOException;

import retrofit2.Call;
import test.com.vk.vkmobile.api.ApiFactory;
import test.com.vk.vkmobile.api.ApiResponse;
import test.com.vk.vkmobile.api.services.NewsFeedService;
import test.com.vk.vkmobile.models.feed.NewsFeed;

/**
 * Created by yurak on 12.03.2017.
 */

public class NewsFeedLoader extends BaseLoader {

    public NewsFeedLoader(Context context) {
        super(context);
    }

    @Override
    protected ApiResponse apiCall() throws IOException {
        NewsFeedService service = ApiFactory.getNewsFeedService();
        Log.d("TOKEN", VKAccessToken.currentToken().accessToken);
        Call<NewsFeed> call = service.login("post", 100, VKAccessToken.currentToken().accessToken, 5.62);

//        Log.d("LOGIN_RESPONCE", call.execute().body().toString());
        NewsFeed body = call.execute().body();
        return new ApiResponse().setAnswer(body);
    }
}
