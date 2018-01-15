package com.castoriqbal.masakgitu;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

import me.itangqi.waveloadingview.WaveLoadingView;

/**
 * Created by Castor on 1/9/2018.
 */

public class ResepListAdapter extends RecyclerView.Adapter<ResepListAdapter.ResepListViewHolder> {
    private ArrayList<Resep> listResep = new ArrayList<>();

    public ResepListAdapter(ArrayList<Resep> listResep){
        this.listResep = listResep;
    }

    public ArrayList<Resep> getListResep() {
        return listResep;
    }

    public void setListResep(ArrayList<Resep> listResep) {
        this.listResep = listResep;
    }

    public class ResepListViewHolder extends RecyclerView.ViewHolder{
        private WaveLoadingView waveLoadingView;

        public ResepListViewHolder(View itemView) {
            super(itemView);
            waveLoadingView = itemView.findViewById(R.id.wave_loading_view_list);
        }

        public WaveLoadingView getWaveLoadingView(){
            return waveLoadingView;
        }
    }

    @Override
    public ResepListAdapter.ResepListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resep_list_item, parent, false);
        return new ResepListViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ResepListViewHolder holder, int position) {
        final Resep resep = listResep.get(position);
        holder.getWaveLoadingView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ItemDetailActivity.class);
                intent.putExtra("resep", resep);
                intent.putStringArrayListExtra("listBahan", SearchActivity.listBahan);
                v.getContext().startActivity(intent);
            }
        });
        holder.getWaveLoadingView().setAnimDuration(1700);
        holder.getWaveLoadingView().setTopTitleSize(17);
        holder.getWaveLoadingView().setCenterTitleSize(18);
        holder.getWaveLoadingView().setCenterTitle(resep.getJudulResep());
        holder.getWaveLoadingView().setBottomTitle(new DecimalFormat("#.##").format(resep.getMatch())+"% Match");
        holder.getWaveLoadingView().setProgressValue((int)Math.ceil(resep.getMatch()));
    }

    @Override
    public int getItemCount() {
        return listResep.size();
    }
}
