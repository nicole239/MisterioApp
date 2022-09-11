package com.nicole.misterioapp.utils;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nicole.misterioapp.R;
import com.nicole.misterioapp.data.DataNotebookGUI;
import com.nicole.misterioapp.logic.Game;
import com.nicole.misterioapp.model.Discovery;

public class NotebookAdapter extends RecyclerView.Adapter<NotebookAdapter.NotebookViewHolder> {

    DataNotebookGUI dataNotebookGUI;

    public NotebookAdapter(DataNotebookGUI dataNotebookGUI) {
        this.dataNotebookGUI = dataNotebookGUI;
    }

    @NonNull
    @Override
    public NotebookViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.notebook_item, viewGroup, false);
        return new NotebookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotebookViewHolder holder, int position) {
        holder.getImageView().setImageResource(dataNotebookGUI.images[position]);
        holder.getLayout().setBackgroundColor(dataNotebookGUI.color);
        Discovery discovery = dataNotebookGUI.marked.get(position + 1);
        switch (discovery){
            case UNKNOWN:
                holder.getRadioGroup().check(R.id.btnUnknown);
                break;
            case INNOCENT:
                holder.getRadioGroup().check(R.id.btnInnocent);
                break;
            case GUILTY:
                holder.getRadioGroup().check(R.id.btnGuilty);
                break;
        }

        holder.getRadioGroup().setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch(checkedId){
                    case R.id.btnInnocent:
                        Game.getInstance().Annotate(dataNotebookGUI.cardType, holder.getAdapterPosition(), Discovery.INNOCENT);
                        break;
                    case R.id.btnGuilty:
                        Game.getInstance().Annotate(dataNotebookGUI.cardType, holder.getAdapterPosition(), Discovery.GUILTY);
                        break;
                    case R.id.btnUnknown:
                        Game.getInstance().Annotate(dataNotebookGUI.cardType, holder.getAdapterPosition(), Discovery.UNKNOWN);
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataNotebookGUI.names.length;
    }

    public class NotebookViewHolder extends RecyclerView.ViewHolder {

        private final RadioGroup radioGroup;
        private final ImageView imageView;
        private final LinearLayout layout;

        public NotebookViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgCardIcon);
            layout = itemView.findViewById(R.id.layoutCardNotebook);
            radioGroup = itemView.findViewById(R.id.rbtnNotebookAnnotate);
        }


        public RadioGroup getRadioGroup() {
            return radioGroup;
        }

        public ImageView getImageView() {
            return imageView;
        }

        public LinearLayout getLayout() {
            return layout;
        }
    }
}
