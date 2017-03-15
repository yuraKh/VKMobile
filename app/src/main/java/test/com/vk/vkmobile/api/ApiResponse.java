package test.com.vk.vkmobile.api;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by yurak on 12.03.2017.
 */

public class ApiResponse
{
    @Nullable private Object mAnswer;

    private RequestResult mRequestResult;

    public ApiResponse() {
        mRequestResult = RequestResult.ERROR;
    }

    @NonNull
    public RequestResult getRequestResult() {
        return mRequestResult;
    }

    public ApiResponse setRequestResult(RequestResult requestResult) {
        mRequestResult = requestResult;
        return this;
    }

    @Nullable
    public <T> T getTypedAnswer() {
        if (mAnswer == null) {
            return null;
        }
        //noinspection unchecked
        return (T) mAnswer;
    }

    public ApiResponse setAnswer(@Nullable Object answer) {
        mAnswer = answer;
        return this;
    }

    public void save(Context context) {
    }
}