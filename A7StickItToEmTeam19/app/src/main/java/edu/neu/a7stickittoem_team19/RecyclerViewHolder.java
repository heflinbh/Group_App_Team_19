package edu.neu.a7stickittoem_team19;


import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    TextView user;
    ImageView sticker;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        user = itemView.findViewById(R.id.user);
        sticker = itemView.findViewById(R.id.sticker);
    }
}
