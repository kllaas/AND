package com.klimchuk.and.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.klimchuk.and.App;
import com.klimchuk.and.R;
import com.klimchuk.and.data.InstaPost;
import com.klimchuk.and.data.LoadingCallback;
import com.klimchuk.and.data.Place;
import com.klimchuk.and.data.google.GoogleAPILoader;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by alexey on 13.05.17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;

    private static final int TYPE_ITEM = 1;

    private final Context mContext;

    private List<InstaPost> mItems = new ArrayList<>();

    private Place mPlace;

    public RecyclerAdapter(Place place, List<InstaPost> items, Context context) {
        this.mPlace = place;
        mItems = items;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.rv_item, parent, false);

            return new ViewHolderItem(v);

        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.rv_header, parent, false);

            return new ViewHolderHeader(v);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int p) {
        int position = holder.getAdapterPosition();

        if (holder instanceof ViewHolderItem) {

            ViewHolderItem item = (ViewHolderItem) holder;
            InstaPost post = mItems.get(position - 1);

            Picasso.with(mContext)
                    .load(post.getUrl())
                    .into(item.ivPostPhoto);

            Picasso.with(mContext)
                    .load(post.getProfilePicture())
                    .into(item.ivUserPhoto);

            item.tvDate.setText((new SimpleDateFormat("yyyy.MM.dd")).format(new Date(Integer.valueOf(post.getCreatedTime()))));

            item.tvLikes.setText(post.getLikes() + " likes");
            item.tvName.setText(post.getProfileUsername());

        } else if (holder instanceof ViewHolderHeader) {

            try {
                GoogleAPILoader.getPlaceByPosition(mContext, mPlace.getLatLng(), new LoadingCallback<Place>() {
                    @Override
                    public void onPlaceLoaded(Place place) {

                        ViewHolderHeader header = (ViewHolderHeader) holder;

                        header.tvAddress.setText(place.getAddress());
                        header.tvName.setText(mPlace.getName());
                        header.tvPostsCount.setText(String.valueOf(mPlace.getPostsCount()) + " posts");

                        if (place.getPhotoReference() != null) {
                            header.ivPlacePhoto.setVisibility(View.VISIBLE);

                            Picasso.with(mContext)
                                    .load(App.PLACES_BASE_URL + "/maps/api/place/photo?maxwidth=600&photoreference="
                                            + place.getPhotoReference()
                                            + "&key=" + mContext.getString(R.string.google_api_key))
                                    .into(header.ivPlacePhoto);
                        }
                    }

                    @Override
                    public void onLoadingFailed() {

                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    @Override
    public int getItemCount() {
        return mItems.size() + 1;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public interface OnItemClickListener {
        void onItemClick(InstaPost region);
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_user_name)
        TextView tvName;

        @BindView(R.id.iv_post_photo)
        ImageView ivPostPhoto;

        @BindView(R.id.profile_image)
        ImageView ivUserPhoto;

        @BindView(R.id.tv_likes)
        TextView tvLikes;

        @BindView(R.id.tv_date)
        TextView tvDate;

        View root;

        ViewHolderItem(View v) {
            super(v);

            root = v;
            ButterKnife.bind(this, v);
        }
    }

    class ViewHolderHeader extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_place_name)
        TextView tvName;

        @BindView(R.id.tv_address)
        TextView tvAddress;

        @BindView(R.id.tv_posts_count)
        TextView tvPostsCount;

        @BindView(R.id.iv_place_photo)
        ImageView ivPlacePhoto;

        ViewHolderHeader(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
