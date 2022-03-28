package edu.neu.a7stickittoem_team19;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewHolder> {

    ArrayList<ItemCard> itemList;

    public RecyclerViewAdapter(ArrayList<ItemCard> itemList) {
        this.itemList = itemList;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
        ItemCard currentItem = itemList.get(position);
        holder.user.setText(currentItem.getUser());

        if (currentItem.getSticker() == 1) {
            holder.sticker.setImageResource(R.drawable.smiley);
        } else {
            holder.sticker.setImageResource(R.drawable.upsidedown);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

}
