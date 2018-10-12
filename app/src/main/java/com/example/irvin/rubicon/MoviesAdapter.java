package com.example.irvin.rubicon;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder> {

    private Context mContext;
    private ArrayList<Movies> mMoviesList;

    public MoviesAdapter(Context context, ArrayList<Movies> movies){
        mContext = context;
        mMoviesList = movies;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_row_movies, parent, false);
        return new MoviesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        Movies currentMovie = mMoviesList.get(position);

        String imageUrl = "http://image.tmdb.org/t/p/w185/" + currentMovie.getPoster_path();
        String title = currentMovie.getTitle();

        holder.mTextViewTitle.setText(title);
        Picasso.with(mContext).load(imageUrl).fit().transform(new CropCircleTransformation()).centerInside().into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mMoviesList.size();
    }

    public class MoviesViewHolder extends RecyclerView.ViewHolder{

        public ImageView mImageView;
        public TextView mTextViewTitle;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.movieImage);
            mTextViewTitle = itemView.findViewById(R.id.movieTitle);
        }
    }
}
