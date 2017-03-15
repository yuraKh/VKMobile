package test.com.vk.vkmobile.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.io.IOException;

import test.com.vk.vkmobile.api.ApiResponse;
import test.com.vk.vkmobile.api.RequestResult;

/**
 * Created by yurak on 12.03.2017.
 */

public abstract class BaseLoader extends AsyncTaskLoader<ApiResponse> {

    public BaseLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public ApiResponse loadInBackground() {
        try {
            ApiResponse response = apiCall();
            if (response.getRequestResult() == RequestResult.SUCCESS) {
                response.save(getContext());
                onSuccess();
            } else {
                onError();
            }
            return response;
        } catch (IOException e) {
            onError();
            return new ApiResponse();
        }
    }

    protected void onSuccess() {
    }

    protected void onError() {
    }

    protected abstract ApiResponse apiCall() throws IOException;
}