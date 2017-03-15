
package test.com.vk.vkmobile.models.feed;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Poll {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;
    @SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("votes")
    @Expose
    private Integer votes;
    @SerializedName("answer_id")
    @Expose
    private Integer answerId;
    @SerializedName("answers")
    @Expose
    private List<Answer> answers = null;
    @SerializedName("anonymous")
    @Expose
    private Integer anonymous;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getVotes() {
        return votes;
    }

    public void setVotes(Integer votes) {
        this.votes = votes;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public Integer getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(Integer anonymous) {
        this.anonymous = anonymous;
    }

}
