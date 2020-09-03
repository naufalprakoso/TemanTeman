package com.fj.temanteman

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.fj.temanteman.database.FriendHelper
import com.fj.temanteman.models.Friend
import kotlinx.android.synthetic.main.activity_add_friend.*
import kotlinx.android.synthetic.main.content_add_friend.*

class AddFriendActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var friendHelper: FriendHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_friend)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        fab.setOnClickListener(this)

        friendHelper = FriendHelper(this)
        friendHelper.open()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab -> {
                val initial = edt_initial.text.toString()
                val name = edt_name.text.toString()
                val major = edt_major.text.toString()
                val phone = edt_phone.text.toString()

                when {
                    initial.isEmpty() -> edt_initial.error = getString(R.string.must_be_filled)
                    name.isEmpty() -> edt_name.error = getString(R.string.must_be_filled)
                    major.isEmpty() -> edt_major.error = getString(R.string.must_be_filled)
                    phone.isEmpty() -> edt_phone.error = getString(R.string.must_be_filled)
                    else -> {
                        val friend = Friend(name = name, major = major, initial = initial, phone = phone)
                        friendHelper.insert(friend)

                        Toast.makeText(this, R.string.friend_added, Toast.LENGTH_SHORT).show()
                        finish()
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
