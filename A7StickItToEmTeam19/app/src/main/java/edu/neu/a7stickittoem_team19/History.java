package edu.neu.a7stickittoem_team19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;

public class History extends AppCompatActivity {

    private Button sendButton;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerLayoutManager;
    ArrayList<ItemCard> stickerList = new ArrayList<>();

    ItemCard item = new ItemCard("benjamin", null);
    ItemCard item2 = new ItemCard("yuan", null);
    ItemCard item3 = new ItemCard("chenyang", null);

    String KEY_OF_INSTANCE = "KEY_OF_INSTANCE";
    String NUMBER_OF_ITEMS = "NUMBER_OF_ITEMS";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        stickerList.add(item);
        stickerList.add(item2);
        stickerList.add(item3);
        stickerList.add(item3);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        init(savedInstanceState);


        sendButton = (Button) findViewById(R.id.sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(History.this, Send.class));
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        int size = stickerList == null ? 0 : stickerList.size();
        outState.putInt(NUMBER_OF_ITEMS, size);

        for (int i = 0; i < size; i++) {
            outState.putString(KEY_OF_INSTANCE + i + "0", stickerList.get(i).getUser());
            outState.putString(KEY_OF_INSTANCE + i + "3", stickerList.get(i).getSticker().toString());
        }
        super.onSaveInstanceState(outState);
    }

    private void init(Bundle savedInstanceState) {
        initialItemData(savedInstanceState);
        createRecyclerView();
    }

    private void initialItemData(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(NUMBER_OF_ITEMS)) {
            if (stickerList == null || stickerList.size() == 0) {

                int size = savedInstanceState.getInt(NUMBER_OF_ITEMS);

                for (int i = 0; i < size; i++) {
                    String user = savedInstanceState.getString(KEY_OF_INSTANCE + i + "1");
                    String sticker = savedInstanceState.getString(KEY_OF_INSTANCE + i + "3");

                    ItemCard itemCard = new ItemCard(user, null);

                    stickerList.add(itemCard);
                }
            }
        }
    }

    private void createRecyclerView() {
        recyclerLayoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        recyclerViewAdapter = new RecyclerViewAdapter(stickerList);
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onItemClick(int position) {
                stickerList.get(position).onItemClick(position);

                recyclerViewAdapter.notifyItemChanged(position);
            }

            @Override
            public void onCheckBoxClick(int position) {
                stickerList.get(position).onCheckBoxClick(position);
                recyclerViewAdapter.notifyItemChanged(position);
            }
        };
        recyclerView.setAdapter(recyclerViewAdapter);
        recyclerView.setLayoutManager(recyclerLayoutManager);
    }
}