package com.fj.temanteman.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fj.temanteman.R
import com.fj.temanteman.models.Friend
import kotlinx.android.synthetic.main.item_friend.view.*

import java.util.ArrayList

class FriendAdapter(
    private val listener: (Friend) -> Unit
) : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {

    private var friends = arrayListOf<Friend>()

    fun setFriends(friends: ArrayList<Friend>) {
        this.friends.clear()
        this.friends.addAll(friends)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder =
            ViewHolder(
                    LayoutInflater.from(viewGroup.context).inflate(
                            R.layout.item_friend,
                            viewGroup,
                            false
                    )
            )

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) =
            viewHolder.bindItem(friends[i], listener)

    override fun getItemCount(): Int = friends.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(friend: Friend, listener: (Friend) -> Unit) {
            itemView.txt_name.text = friend.name
            itemView.txt_major.text = friend.major
            itemView.txt_initial.text = friend.initial

            itemView.cv_friend.setOnClickListener {
                listener(friend)
            }
        }
    }
}
