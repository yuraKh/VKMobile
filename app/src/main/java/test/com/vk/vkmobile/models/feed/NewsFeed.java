
package test.com.vk.vkmobile.models.feed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewsFeed {

    @SerializedName("response")
    @Expose
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

}
