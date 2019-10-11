package com.fj.temanteman

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager

import com.fj.temanteman.adapters.FriendAdapter
import com.fj.temanteman.data.Const
import com.fj.temanteman.database.FriendHelper
import com.fj.temanteman.models.Friend
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

import java.util.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var friendAdapter: FriendAdapter
    private lateinit var friendHelper: FriendHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        friendHelper = FriendHelper(this)
        friendHelper.open()

        rv_data.layoutManager = GridLayoutManager(this, 2)
        friendAdapter = FriendAdapter {
            val intent = Intent(this, FriendDetailActivity::class.java)
            intent.putExtra(Const.KEY_FRIEND, it)
            startActivity(intent)
        }
        rv_data.adapter = friendAdapter

        fab.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab -> {
                val intent = Intent(this, AddFriendActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        LoadFriendData().execute()
    }

    inner class LoadFriendData : AsyncTask<Void, Void, ArrayList<Friend>>() {
        override fun doInBackground(vararg voids: Void): ArrayList<Friend> {
            return friendHelper.query()
        }

        override fun onPostExecute(friendsList: ArrayList<Friend>) {
            super.onPostExecute(friendsList)

            friendAdapter.setFriends(friendsList)
            friendAdapter.notifyDataSetChanged()
        }
    }
}
