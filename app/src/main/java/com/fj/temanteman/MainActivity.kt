package com.fj.temanteman

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View

import com.fj.temanteman.adapters.FriendAdapter
import com.fj.temanteman.data.Const
import com.fj.temanteman.database.FriendHelper
import com.fj.temanteman.models.Friend

import java.util.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var friendAdapter: FriendAdapter
    private lateinit var friendHelper: FriendHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener(this)

        val recyclerView = findViewById<RecyclerView>(R.id.rv_data)
        recyclerView.layoutManager = GridLayoutManager(this, 2)

        friendHelper = FriendHelper(this)
        friendHelper.open()

        friendAdapter = FriendAdapter {
            val intent = Intent(this, FriendDetailActivity::class.java)
            intent.putExtra(Const.KEY_FRIEND, it)
            startActivity(intent)
        }
        recyclerView.adapter = friendAdapter
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
