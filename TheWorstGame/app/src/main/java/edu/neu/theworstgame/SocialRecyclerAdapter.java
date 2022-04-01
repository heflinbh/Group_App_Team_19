package edu.neu.theworstgame;

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

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            friendNameTV = itemView.findViewById(R.id.friendUsernameTextView);
        }
    }

    @NonNull
    @Override
    public SocialRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_friends, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SocialRecyclerAdapter.MyViewHolder holder, int position) {
        String friendName = usersList.get(position).getUsername();
        holder.friendNameTV.setText(friendName);

    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }
}
