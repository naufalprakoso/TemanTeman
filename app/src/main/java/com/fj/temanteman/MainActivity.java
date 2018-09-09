package com.fj.temanteman;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fj.temanteman.adapters.FriendAdapter;
import com.fj.temanteman.database.FriendHelper;
import com.fj.temanteman.models.Friend;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FriendAdapter friendAdapter;
    private FriendHelper friendHelper;
    private ArrayList<Friend> friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.rv_data);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        friends = new ArrayList<>();
        friendHelper = new FriendHelper(this);
        friendHelper.open();

        friendAdapter = new FriendAdapter(this);
        friendAdapter.setFriends(friends);
        recyclerView.setAdapter(friendAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                Intent intent = new Intent(this, AddFriendActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        new LoadFriendData().execute();
    }

    public class LoadFriendData extends AsyncTask<Void, Void, ArrayList<Friend>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected ArrayList<Friend> doInBackground(Void... voids) {
            return friendHelper.query();
        }

        @Override
        protected void onPostExecute(ArrayList<Friend> friendsList) {
            super.onPostExecute(friendsList);

            friends = friendsList;
            friendAdapter.setFriends(friendsList);
            friendAdapter.notifyDataSetChanged();
        }
    }
}
