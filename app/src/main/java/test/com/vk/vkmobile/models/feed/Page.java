
package test.com.vk.vkmobile.models.feed;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Page {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("group_id")
    @Expose
    private Integer groupId;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("who_can_view")
    @Expose
    private Integer whoCanView;
    @SerializedName("who_can_edit")
    @Expose
    private Integer whoCanEdit;
    @SerializedName("edited")
    @Expose
    private Integer edited;
    @SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("view_url")
    @Expose
    private String viewUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getWhoCanView() {
        return whoCanView;
    }

    public void setWhoCanView(Integer whoCanView) {
        this.whoCanView = whoCanView;
    }

    public Integer getWhoCanEdit() {
        return whoCanEdit;
    }

    public void setWhoCanEdit(Integer whoCanEdit) {
        this.whoCanEdit = whoCanEdit;
    }

    public Integer getEdited() {
        return edited;
    }

    public void setEdited(Integer edited) {
        this.edited = edited;
    }

    public Integer getCreated() {
        return created;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getViewUrl() {
        return viewUrl;
    }

    public void setViewUrl(String viewUrl) {
        this.viewUrl = viewUrl;
    }

}
