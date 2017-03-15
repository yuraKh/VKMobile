package test.com.vk.vkmobile.adapters;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import test.com.vk.vkmobile.R;
import test.com.vk.vkmobile.halper.CircleTransform;
import test.com.vk.vkmobile.models.feed.Attachment;
import test.com.vk.vkmobile.models.feed.CopyHistory;
import test.com.vk.vkmobile.models.feed.Group;
import test.com.vk.vkmobile.models.feed.Item;
import test.com.vk.vkmobile.models.feed.Profile;
import test.com.vk.vkmobile.models.feed.Response;

import static com.vk.sdk.api.model.VKAttachments.TYPE_PHOTO;
import static com.vk.sdk.api.model.VKAttachments.TYPE_VIDEO;

/**
 * Created by yurak on 13.03.2017.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder>
{

    //private Response response;
    private List<Item> items;
    private List<Group> groups;
    private List<Profile> profiles;

    private LayoutInflater mInflater;
    private Context context;


    public FeedAdapter(Context context, Response response)
    {
       // this.response = response;

        items = response.getItems();
        groups = response.getGroups();
        profiles = response.getProfiles();
        this.context = context;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.feed, parent, false);
        return new ViewHolder(v);
    }



    @Override
    public void onBindViewHolder(final FeedAdapter.ViewHolder viewHolder, int position) {

        long timeMillis = items.get(position).getDate()*1000L;
        Date currentDate = new Date(timeMillis);
        DateFormat format = new SimpleDateFormat("HH:mm");
        viewHolder.postDate.setText(format.format(currentDate));

        List<Attachment> attachments = items.get(position).getAttachments();
        addAttachments(attachments, viewHolder.recyclerView);
        /*if (attachments != null) {
            if (getPhotoCount(attachments) == 1)
                viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
            else
                viewHolder.recyclerView.setLayoutManager(new GridLayoutManager(context, 2));

            viewHolder.recyclerView.setAdapter(new AttachmentsAdapter(context, attachments));
        } else {
            viewHolder.recyclerView.setVisibility(View.GONE);
        }*/

        int sourceId = items.get(position).getSourceId();
        if (sourceId < 0) {
            Group group = getGroupById(sourceId, groups);
            Picasso.with(context).load(group.getPhoto100()).transform(new CircleTransform())
                    .fit().centerCrop().into(viewHolder.photo);
            viewHolder.name.setText(group.getName());
        } else {
            Profile profile = getProfileById(sourceId, profiles);
            Picasso.with(context).load(profile.getPhoto100()).transform(new CircleTransform())
                    .fit().centerCrop().into(viewHolder.photo);
            viewHolder.name.setText(profile.getFirstName() + " " + profile.getLastName());
        }
        viewHolder.newsText.setText(items.get(position).getText());

        if(items.get(position).getCopyHistory() != null){
            viewHolder.repost.setVisibility(View.VISIBLE);
           CopyHistory cp = items.get(position).getCopyHistory().get(0);
            if (cp.getOwnerId() < 0){
                Group group = getGroupById(cp.getOwnerId(), groups);
                viewHolder.repostText.setText(group.getName());
            }else {
                Profile profile = getProfileById(cp.getOwnerId(), profiles);
                viewHolder.repostText.setText(profile.getFirstName() + " " + profile.getLastName());
            }
            viewHolder.newsText.setText(cp.getText());
            addAttachments(cp.getAttachments(), viewHolder.recyclerView);
        }

    }

    private void addAttachments(List<Attachment> attachments, RecyclerView recyclerView) {
        if (attachments != null) {
            recyclerView.setVisibility(View.VISIBLE);
            int photoCount = getPhotoCount(attachments);
            if (photoCount == 1)
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            else
                recyclerView.setLayoutManager(new GridLayoutManager(context, 2));
            recyclerView.setAdapter(new AttachmentsAdapter(context, attachments, photoCount));
        } else {
            recyclerView.setVisibility(View.GONE);
        }
    }

    private int getPhotoCount(List<Attachment> attachments){
        int count = 0;
        for(Attachment a:attachments){
            if (a.getType().equals(TYPE_PHOTO)||a.equals(TYPE_VIDEO))
                count++;
        }
        return count;
    }

    @Override
    public int getItemCount()
    {
        // TODO: Implement this method
        return items.size();
    }

    public Item getItem(int p)
    {
        return items.get(p);
    }

    private Profile getProfileById(int itemSourceId, List<Profile> profileList) {
        for (int i = 0; i < profileList.size(); i++) {
            if (itemSourceId == profileList.get(i).getId()) return profileList.get(i);
        }
        return null;
    }

    private Group getGroupById(int itemSourceId, List<Group> groupList) {
        for (int i = 0; i < groupList.size(); i++) {
            if (Math.abs(itemSourceId) == groupList.get(i).getId()) return groupList.get(i);
        }
        return null;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        public final ImageView photo;
        public final LinearLayout repost;
        public final TextView name;
        public final TextView newsText;
        public final TextView postDate;
        public final TextView repostText;
        public final RecyclerView recyclerView;

        public ViewHolder(View v)
        {
            super(v);
            photo = (ImageView)v.findViewById(R.id.img_posts_news_user_photo);
            repost = (LinearLayout) v.findViewById(R.id.original_name_layout);
            name = (TextView)v.findViewById(R.id.tv_posts_news_user_name);
            newsText = (TextView)v.findViewById(R.id.tv_posts_news_text);
            repostText = (TextView)v.findViewById(R.id.original_name);
            postDate = (TextView)v.findViewById(R.id.tv_posts_news_ago);
            recyclerView = (RecyclerView) v.findViewById(R.id.recyclerViewAtt);
        }

        @Override
        public void onClick(View p1) {
        }
    }
}