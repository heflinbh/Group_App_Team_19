package edu.neu.a7stickittoem_team19;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class ItemCard implements ItemClickListener {

    String user;
    Drawable sticker;

    public ItemCard(String user, Drawable sticker) {
        this.user = user;
        this.sticker = sticker;
    }

    @Override
    public void onItemClick(int position) {
        return;
    }

    @Override
    public void onCheckBoxClick(int position) {
        return;
    }

    public String getUser() {
        return user;
    }

    public Drawable getSticker() {
        return sticker;
    }
}
