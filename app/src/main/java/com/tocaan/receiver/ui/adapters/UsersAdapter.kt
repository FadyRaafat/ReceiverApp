package com.tocaan.receiver.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import com.tocaan.receiver.R
import com.tocaan.receiver.api.models.User
import com.tocaan.receiver.databinding.ItemUsersBinding
import com.tocaan.receiver.ui.utils.DataBoundListAdapter

class UsersAdapter(
) : DataBoundListAdapter<User, ItemUsersBinding>
    (diffCallback = object : DiffUtil.ItemCallback<User>() {
    override fun areContentsTheSame(
        oldItem: User,
        newItem: User
    ): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(
        oldItem: User,
        newItem: User
    ): Boolean {
        return oldItem == newItem
    }

}
) {
    override fun createBinding(parent: ViewGroup): ItemUsersBinding {
        return DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_users,
            parent,
            false
        )
    }

    override fun bind(
        binding: ItemUsersBinding,
        item: User,
        position: Int,
        adapterPosition: Int
    ) {
        binding.item = item
    }

}