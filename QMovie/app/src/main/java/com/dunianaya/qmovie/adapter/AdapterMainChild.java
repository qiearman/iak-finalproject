package com.dunianaya.qmovie.adapter;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dunianaya.qmovie.R;
import com.dunianaya.qmovie.model.Movie;

import java.util.List;

/**
 * Created by Qi on 8/29/2017.
 */

public class AdapterMainChild extends RecyclerView.Adapter<AdapterMainChild.ThisViewHolder>{

    List<Movie> mMovies;
    Resources mRes;

    public AdapterMainChild(List<Movie> movies) {
        mMovies = movies;
    }

    @Override
    public AdapterMainChild.ThisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_main, parent, false);
        mRes = view.getResources();
        return new ThisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterMainChild.ThisViewHolder holder, int position) {
        Movie movie = mMovies.get(position);
        holder.mTvTitle.setText(movie.getTitle());
        holder.mIvMovie.setImageDrawable(mRes.getDrawable(R.drawable.iaklogo));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class ThisViewHolder extends RecyclerView.ViewHolder {
        ImageView mIvMovie;
        TextView mTvTitle;

        public ThisViewHolder(View view) {
            super(view);
            mIvMovie = (ImageView) view.findViewById(R.id.iv_Movie);
            mTvTitle = (TextView)view.findViewById(R.id.tv_Title);
        }
    }
}
