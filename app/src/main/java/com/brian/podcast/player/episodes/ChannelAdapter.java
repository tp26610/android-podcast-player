package com.brian.podcast.player.episodes;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.brian.podcast.player.R;
import com.bumptech.glide.Glide;

public class ChannelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_COVER_IMAGE = 1;
    private static final int VIEW_TYPE_EPISODE = 2;

    private static final int COVER_IMAGE_ITEM_COUNT = 1;

    private Channel channel;

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? VIEW_TYPE_COVER_IMAGE : VIEW_TYPE_EPISODE;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case VIEW_TYPE_COVER_IMAGE: {
                View view = inflater.inflate(R.layout.episodes_item_cover_image, parent, false);
                return new CoverImageViewHolder(view);
            }
            //            case VIEW_TYPE_EPISODE: {
            //                View view = inflater.inflate(R.layout.episodes_item_episode, parent, false);
            //                return new EpisodeViewHolder(view);
            //            }
            default:
                throw new RuntimeException("onCreateViewHolder >> unknown viewType=" + viewType);
        }
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        int viewType = holder.getItemViewType();
        Log.i("Brian", "onBindViewHolder >> position=" + position + " viewType=" + viewType + " VIEW_TYPE_COVER_IMAGE=" + VIEW_TYPE_COVER_IMAGE);
        switch (viewType) {
            case VIEW_TYPE_COVER_IMAGE: {
                CoverImageViewHolder viewHolder = (CoverImageViewHolder) holder;
                AppCompatImageView coverImageView = viewHolder.imageView;

                if (channel != null && channel.coverImageUrl != null) {
                    Glide.with(coverImageView)
                            .load(channel.coverImageUrl)
                            .into(coverImageView);
                }

                break;
            }
            case VIEW_TYPE_EPISODE: {
                EpisodeViewHolder viewHolder = (EpisodeViewHolder) holder;
                break;
            }
            default:
                throw new RuntimeException("onBindViewHolder >> unknown viewType=" + viewType);
        }
    }

    @Override
    public int getItemCount() {
        int episodesCount = 0;
        //        if (channel != null && channel.episodes != null) {
        //            episodesCount = channel.episodes.size();
        //        }
        Log.i("Brian", "getItemCount >> " + (COVER_IMAGE_ITEM_COUNT + episodesCount));
        return COVER_IMAGE_ITEM_COUNT + episodesCount;
    }


    public static class CoverImageViewHolder extends RecyclerView.ViewHolder {
        public final AppCompatImageView imageView;

        public CoverImageViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.episodes_item_coverImage);
        }
    }

    public static class EpisodeViewHolder extends RecyclerView.ViewHolder {
        public EpisodeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}