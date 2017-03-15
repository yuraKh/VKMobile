package test.com.vk.vkmobile.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import test.com.vk.vkmobile.halper.ScaleToFitWidthHeightTransform;
import test.com.vk.vkmobile.models.feed.Attachment;

import static com.vk.sdk.api.model.VKAttachments.TYPE_ALBUM;
import static com.vk.sdk.api.model.VKAttachments.TYPE_APP;
import static com.vk.sdk.api.model.VKAttachments.TYPE_AUDIO;
import static com.vk.sdk.api.model.VKAttachments.TYPE_DOC;
import static com.vk.sdk.api.model.VKAttachments.TYPE_LINK;
import static com.vk.sdk.api.model.VKAttachments.TYPE_NOTE;
import static com.vk.sdk.api.model.VKAttachments.TYPE_PHOTO;
import static com.vk.sdk.api.model.VKAttachments.TYPE_POLL;
import static com.vk.sdk.api.model.VKAttachments.TYPE_POST;
import static com.vk.sdk.api.model.VKAttachments.TYPE_POSTED_PHOTO;
import static com.vk.sdk.api.model.VKAttachments.TYPE_VIDEO;
import static com.vk.sdk.api.model.VKAttachments.TYPE_WIKI_PAGE;

/**
 * Created by yurak on 14.03.2017.
 */

public class AttachmentsAdapter extends RecyclerView.Adapter<AttachmentsAdapter.ViewHolder> {

    private List<Attachment> attachments;
    private Context context;
    private LayoutInflater mInflater;
    private int photoCount;

    public AttachmentsAdapter(Context context, List<Attachment> attachments, int photoCount) {
        this.attachments = attachments;
        this.context = context;
        this.photoCount = photoCount;
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView view = new ImageView(context);
        view.setPadding(1,1,1,1);

        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Attachment attachment = attachments.get(position);

        switch (attachment.getType()){
            case TYPE_PHOTO:
                loadAttachment(holder.imageView, attachment.getPhoto().getPhoto604());
                break;
            case TYPE_VIDEO:
                loadAttachment(holder.imageView, attachment.getVideo().getPhoto640());
                break;
            case TYPE_DOC:
                break;
            //loadAttachment(holder.imageView, attachment.getDoc().ge());
            case TYPE_AUDIO:
                break;
            case TYPE_LINK:
                //loadAttachment(holder.imageView, attachment.getLink().getPhoto().getPhoto604());
                break;
            case TYPE_POLL:
                break;
            case TYPE_ALBUM:
                break;
            case TYPE_APP:
                break;
            case TYPE_NOTE:
                break;
            case TYPE_WIKI_PAGE:
                break;
            case TYPE_POSTED_PHOTO:
                break;
            case TYPE_POST:
                break;
        }
        /*if (attachment.getType().equals(TYPE_PHOTO)) {
            if (getItemCount() == 1)
                Picasso.with(context).load(attachment.getPhoto().getPhoto604())
                        .transform(new ScaleToFitWidthHeightTransform(720, false)).into(holder.imageView);
            else
                Picasso.with(context).load(attachment.getPhoto().getPhoto604())
                        .transform(new ScaleToFitWidthHeightTransform(360, false))
                        /*.resize(360, 340).centerCrop().into(holder.imageView);
      /*  } else if (attachment.getType().equals(TYPE_VIDEO)) {
            if (getItemCount() == 1)
                Picasso.with(context).load(attachment.getVideo().getPhoto640())
                        .transform(new ScaleToFitWidthHeightTransform(720, false)).into(holder.imageView);
            else
                Picasso.with(context).load(attachment.getVideo().getPhoto320())
                        .transform(new ScaleToFitWidthHeightTransform(360, false))
                        /*.resize(360, 310).centerCrop()*//*.into(holder.imageView);
        }*/

    }



    private void loadAttachment(ImageView imageView, String url) {
        if (photoCount == 1)
            Picasso.with(context).load(url)
                    .transform(new ScaleToFitWidthHeightTransform(720, false)).into(imageView);
        else
            Picasso.with(context).load(url)
                    .transform(new ScaleToFitWidthHeightTransform(360, false)).into(imageView);
}

    @Override
    public int getItemCount() {
        return attachments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public ViewHolder(ImageView itemView) {
            super(itemView);
            imageView = itemView;
        }
    }
}
