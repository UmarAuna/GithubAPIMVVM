package com.example.githubapitest.adapter

import com.example.githubapitest.databinding.ItemGithubApiBinding
import com.example.githubapitest.model.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubapitest.R

class GithubApiAdapter(
    private val fetchApi: ArrayList<Item>,
    private val openDetailClick: OpenDetailClickListener
) : ListAdapter<Item, GithubApiAdapter.DataViewHolder>(DIFF_CALLBACK) {

    class DataViewHolder(private val itemBinding: ItemGithubApiBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(item: Item, openDetailClick: OpenDetailClickListener) {
            itemBinding.loginTextView.text = item.login
            itemBinding.htmlUrlTextView.text = item.htmlUrl

            Glide.with(itemView.context)
                .load(item.avatarUrl)
                .placeholder(R.drawable.no_image)
                .into(itemBinding.iconImageView)

            itemBinding.cardView.setOnClickListener {
                openDetailClick.openDetailClickListener(item)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataViewHolder {
        val itemBinding = ItemGithubApiBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(fetchApi[position], openDetailClick)
    }

    fun addData(list: List<Item>) {
        fetchApi.addAll(list)
    }

    interface OpenDetailClickListener {
        fun openDetailClickListener(note: Item)
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Item> = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem == newItem
            }
        }
    }
}
