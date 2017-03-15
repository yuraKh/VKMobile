
package test.com.vk.vkmobile.models.feed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Answer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("votes")
    @Expose
    private Integer votes;
    @SerializedName("rate")
    @Expose
    private Double rate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

}
