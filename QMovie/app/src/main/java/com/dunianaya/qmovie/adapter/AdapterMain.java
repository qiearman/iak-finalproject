package com.dunianaya.qmovie.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dunianaya.qmovie.R;
import com.dunianaya.qmovie.model.Section;

import java.util.List;

/**
 * Created by Qi on 8/29/2017.
 */

public class AdapterMain extends RecyclerView.Adapter<AdapterMain.ThisViewHolder> {

    List<Section> mSections;
    public AdapterMain(List<Section> section) {
        mSections = section;
    }

    @Override
    public void onBindViewHolder(ThisViewHolder holder, int position) {
        Section section = mSections.get(position);
        holder.tv_sectionTitle.setText(section.getTitle());
        Log.d("HANCAmSections", mSections.size() + "");
        Log.d("HANCASection", section.getMovies().size() + "");
        AdapterMainChild adapterChild = new AdapterMainChild(section.getMovies());
        holder.rv_Body.setAdapter(adapterChild);
    }

    @Override
    public ThisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate( R.layout.item_list_main, parent, false);
        return new ThisViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mSections.size();
    }

    public class ThisViewHolder extends RecyclerView.ViewHolder {
        TextView tv_sectionTitle;
        TextView tv_MoreButton;
        RecyclerView rv_Body;

        public ThisViewHolder(View view) {
            super(view);

            tv_sectionTitle = (TextView)view.findViewById(R.id.tv_SectionTitle);
            tv_MoreButton = (TextView)view.findViewById(R.id.tv_MoreButton);
            rv_Body = (RecyclerView)view.findViewById(R.id.rv_Body);
        }
    }
}
