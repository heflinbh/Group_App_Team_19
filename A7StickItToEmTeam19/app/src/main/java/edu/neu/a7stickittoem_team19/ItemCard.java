package edu.neu.a7stickittoem_team19;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class ItemCard implements ItemClickListener {

    String user;
    int stickerType;

    public ItemCard(String user, int stickerType) {
        this.user = user;
        this.stickerType = stickerType;
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

    public int getSticker() {
        return stickerType;
    }
}
