package com.klimchuk.and.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.klimchuk.and.R;
import com.klimchuk.and.data.InstaPost;
import com.klimchuk.and.data.Place;

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

    private List<InstaPost> mItems;

    private Place mPlace;

    public RecyclerAdapter(List<InstaPost> items, Context context, Place place) {
        mPlace = place;
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

        } else if (holder instanceof ViewHolderHeader) {

            ViewHolderHeader header = (ViewHolderHeader) holder;

            header.tvAddress.setText(mPlace.getAddress());
            header.tvAddress.setText(mPlace.getName());
            header.tvAddress.setText(mPlace.getPostsCount());
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
        return mItems.size();
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public interface OnItemClickListener {
        void onItemClick(InstaPost region);
    }

    class ViewHolderItem extends RecyclerView.ViewHolder {
/*

        @BindView(R.id.tv_name)
        TextView tvName;

        @BindView(R.id.iv_icon)
        ImageView ivIcon;

        @BindView(R.id.iv_icon_download)
        ImageView ivDownload;

        @BindView(R.id.line)
        View line;
*/

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

        ViewHolderHeader(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
