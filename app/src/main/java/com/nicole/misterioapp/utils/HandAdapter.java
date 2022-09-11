package com.nicole.misterioapp.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nicole.misterioapp.R;
import com.nicole.misterioapp.data.DataHandGUI;

public class HandAdapter extends RecyclerView.Adapter<HandAdapter.HandViewHolder> {

    private DataHandGUI dataHandGUI;

    public HandAdapter(DataHandGUI dataHandGUI){
        this.dataHandGUI = dataHandGUI;
    }

    @NonNull
    @Override
    public HandViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hand_item, viewGroup, false);
        return new HandAdapter.HandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HandViewHolder holder, int position) {
        holder.setBtnSelectCard(dataHandGUI.getImages()[position]);
    }

    @Override
    public int getItemCount() {
        return dataHandGUI.getImages().length;
    }

    public class HandViewHolder extends RecyclerView.ViewHolder {

        private ImageButton btnSelectCard;

        public HandViewHolder(@NonNull View itemView) {
            super(itemView);

            btnSelectCard = itemView.findViewById(R.id.btnCardSelected);
        }

        public void setBtnSelectCard(int idImageResource) {
            this.btnSelectCard.setImageResource(idImageResource);
        }
    }
}
