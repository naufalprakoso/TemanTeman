package com.fj.temanteman;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fj.temanteman.database.FriendHelper;
import com.fj.temanteman.models.Friend;

public class AddFriendActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtInitial, edtName, edtMajor, edtPhone;
    private FriendHelper friendHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        edtInitial = findViewById(R.id.edt_initial);
        edtName = findViewById(R.id.edt_name);
        edtMajor = findViewById(R.id.edt_major);
        edtPhone = findViewById(R.id.edt_phone);

        friendHelper = new FriendHelper(this);
        friendHelper.open();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                String initial = edtInitial.getText().toString();
                String name = edtName.getText().toString();
                String major = edtMajor.getText().toString();
                String phone = edtPhone.getText().toString();

                if (initial.isEmpty()) {
                    edtInitial.setError(getString(R.string.must_be_filled));
                } else if (name.isEmpty()) {
                    edtName.setError(getString(R.string.must_be_filled));
                } else if (major.isEmpty()) {
                    edtMajor.setError(getString(R.string.must_be_filled));
                } else if (phone.isEmpty()) {
                    edtPhone.setError(getString(R.string.must_be_filled));
                } else {
                    Friend friend = new Friend();
                    friend.setInitial(initial);
                    friend.setName(name);
                    friend.setMajor(major);
                    friend.setPhone(phone);

                    friendHelper.insert(friend);

                    Toast.makeText(this, R.string.friend_added, Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
