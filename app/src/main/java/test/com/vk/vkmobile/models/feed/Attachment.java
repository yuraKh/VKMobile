
package test.com.vk.vkmobile.models.feed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attachment {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("photo")
    @Expose
    private Photo photo;
    @SerializedName("link")
    @Expose
    private Link link;
    @SerializedName("poll")
    @Expose
    private Poll poll;
    @SerializedName("doc")
    @Expose
    private Doc doc;
    @SerializedName("video")
    @Expose
    private Video video;
    @SerializedName("page")
    @Expose
    private Page page;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Poll getPoll() {
        return poll;
    }

    public void setPoll(Poll poll) {
        this.poll = poll;
    }

    public Doc getDoc() {
        return doc;
    }

    public void setDoc(Doc doc) {
        this.doc = doc;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

}
