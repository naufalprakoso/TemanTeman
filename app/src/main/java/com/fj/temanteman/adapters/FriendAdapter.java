package com.fj.temanteman.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fj.temanteman.FriendDetailActivity;
import com.fj.temanteman.R;
import com.fj.temanteman.data.Consts;
import com.fj.temanteman.models.Friend;

import java.util.ArrayList;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Friend> friends;

    public FriendAdapter(Activity activity) {
        this.context = activity;
    }

    public void setFriends(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_friend, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.txtName.setText(friends.get(i).getName());
        viewHolder.txtMajor.setText(friends.get(i).getMajor());
        viewHolder.txtInitial.setText(friends.get(i).getInitial());

        viewHolder.cvFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FriendDetailActivity.class);
                intent.putExtra(Consts.KEY_ID, friends.get(i).getId());
                intent.putExtra(Consts.KEY_INITIAL, friends.get(i).getInitial());
                intent.putExtra(Consts.KEY_NAME, friends.get(i).getName());
                intent.putExtra(Consts.KEY_MAJOR, friends.get(i).getMajor());
                intent.putExtra(Consts.KEY_PHONE, friends.get(i).getPhone());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtName, txtInitial, txtMajor;
        CardView cvFriend;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txt_name);
            txtMajor = itemView.findViewById(R.id.txt_major);
            txtInitial = itemView.findViewById(R.id.txt_initial);
            cvFriend = itemView.findViewById(R.id.cv_friend);
        }
    }
}
