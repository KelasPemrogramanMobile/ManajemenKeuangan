package com.ta.monman.monman;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder> {


    private Context mCtx;
    private List<History> historyList;

    public HistoryAdapter(Context mCtx, List<History> historyList) {
        this.mCtx = mCtx;
        this.historyList = historyList;
    }

    @Override
    public HistoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.history_list, null);
        return new HistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryViewHolder holder, int position) {
        History history = historyList.get(position);

//        //loading the image
        if(history.getTransaksi().equalsIgnoreCase("Pemasukkan")) {
            Glide.with(mCtx)
                    .load(R.drawable.ic_arrow_downward_green_400_24dp)
                    .into(holder.imageView);
        }else{
            Glide.with(mCtx)
                    .load(R.drawable.ic_arrow_upward_red_400_24dp)
                    .into(holder.imageView);
        }

        holder.textViewTitle.setText(history.getDetail());
        holder.txtHarga.setText(String.valueOf(history.getNominal()));
        holder.textViewTransaksi.setText(String.valueOf(history.getTransaksi()));
        holder.txtTanggal.setText(history.getTanggal());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }

    class HistoryViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, txtHarga,txtTanggal, textViewTransaksi;
        ImageView imageView;

        public HistoryViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            txtTanggal = itemView.findViewById(R.id.txtTanggal);
            txtHarga = itemView.findViewById(R.id.txtHarga);
            textViewTransaksi = itemView.findViewById(R.id.textViewTransaksi);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}