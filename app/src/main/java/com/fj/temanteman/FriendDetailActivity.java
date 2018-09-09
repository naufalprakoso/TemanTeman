package com.fj.temanteman;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.fj.temanteman.data.Consts;
import com.fj.temanteman.database.FriendHelper;

public class FriendDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private FriendHelper friendHelper;
    private int id;
    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        ImageView imgDelete = findViewById(R.id.img_delete);
        imgDelete.setOnClickListener(this);

        String initial = getIntent().getStringExtra(Consts.KEY_INITIAL);
        String name = getIntent().getStringExtra(Consts.KEY_NAME);
        String major = getIntent().getStringExtra(Consts.KEY_MAJOR);
        phone = getIntent().getStringExtra(Consts.KEY_PHONE);
        id = getIntent().getIntExtra(Consts.KEY_ID, 0);

        TextView txtName = findViewById(R.id.txt_name);
        TextView txtMajor = findViewById(R.id.txt_major);

        getSupportActionBar().setTitle(initial);
        txtMajor.setText(major);
        txtName.setText(name);

        friendHelper = new FriendHelper(this);
        friendHelper.open();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                Intent intent = new Intent(this, SendMessageActivity.class);
                intent.putExtra(Consts.KEY_PHONE, phone);
                startActivity(intent);
                break;
            case R.id.img_delete:
                friendHelper.delete(id);
                Toast.makeText(this, "Friend deleted", Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
