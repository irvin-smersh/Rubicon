package com.example.irvin.rubicon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>{

    private Context mContext;
    private ArrayList<TvShow> mTvShowsList;

    public TvShowAdapter(Context context, ArrayList<TvShow> tvShows){
        mContext = context;
        mTvShowsList = tvShows;
    }

    @Override
    public TvShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_row_tvshows, parent, false);
        return new TvShowViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TvShowViewHolder holder, int position) {
        TvShow currentTvShow = mTvShowsList.get(position);

        String imageUrl = "http://image.tmdb.org/t/p/w185/" + currentTvShow.getPoster_path();
        String title = currentTvShow.getName();

        holder.mTextViewTitle.setText(title);
        Picasso.with(mContext).load(imageUrl).fit().transform(new CropCircleTransformation()).centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mTvShowsList.size();
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mTextViewTitle;

        public TvShowViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.tvshowImage);
            mTextViewTitle = itemView.findViewById(R.id.tvshowTitle);
        }
    }

}
