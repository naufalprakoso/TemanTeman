package com.fj.temanteman

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.fj.temanteman.data.Const
import com.fj.temanteman.database.FriendHelper
import com.fj.temanteman.models.Friend
import kotlinx.android.synthetic.main.activity_friend_detail.*
import kotlinx.android.synthetic.main.item_friend.*

class FriendDetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var friendHelper: FriendHelper
    private lateinit var friend: Friend

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friend_detail)
        setSupportActionBar(toolbar)

        friend = intent.getParcelableExtra(Const.KEY_FRIEND)

        supportActionBar?.title = friend.initial
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        fab.setOnClickListener(this)

        img_delete.setOnClickListener(this)

        txt_major.text = friend.major
        txt_name.text = friend.name

        friendHelper = FriendHelper(this)
        friendHelper.open()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab -> {
                val intent = Intent(this, SendMessageActivity::class.java)
                intent.putExtra(Const.KEY_PHONE, friend.phone)
                startActivity(intent)
            }
            R.id.img_delete -> {
                friendHelper.delete(friend.id)
                Toast.makeText(this, "Friend deleted", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
