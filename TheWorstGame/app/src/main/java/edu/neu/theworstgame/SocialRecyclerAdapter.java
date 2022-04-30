package edu.neu.theworstgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SocialRecyclerAdapter extends RecyclerView.Adapter<SocialRecyclerAdapter.MyViewHolder> {

    private ArrayList<User> usersList;

    public SocialRecyclerAdapter(ArrayList<User> users) {
        this.usersList = users;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView friendNameTV;
        private TextView friendRankTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            friendNameTV = itemView.findViewById(R.id.friendUsernameTextView);
            friendRankTextView = itemView.findViewById(R.id.friendRankTextView);
        }
    }

    @NonNull
    @Override
    public SocialRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.list_friends, parent, false);

        return new MyViewHolder(contactView);
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_friends, parent, false);
//        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SocialRecyclerAdapter.MyViewHolder holder, int position) {

        User user = usersList.get(position);

        String friendName = user.getUsername();
        holder.friendNameTV.setText(friendName);
        String tier = user.calculateTier();
        holder.friendRankTextView.setText(tier);

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
