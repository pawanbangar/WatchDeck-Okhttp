package com.example.watchdeck.ui.issues

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.watchdeck.data.entities.Data
import com.example.watchdeck.data.entities.Issue
import com.example.watchdeck.databinding.ItemIssueBinding
import java.text.SimpleDateFormat

class IssuesAdapter(private val listener: IssueItemListener) : RecyclerView.Adapter<IssueViewHolder>() {

    interface IssueItemListener {
        fun onClickedIssue(data: Data)
    }

    private val items = ArrayList<Issue>()

    fun setItems(items: ArrayList<Issue>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val binding: ItemIssueBinding = ItemIssueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IssueViewHolder(binding, listener)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) = holder.bind(items[position])
}

class IssueViewHolder(private val itemBinding: ItemIssueBinding, private val listener: IssuesAdapter.IssueItemListener) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var issue: Issue

    init {
        itemBinding.root.setOnClickListener(this)
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    fun bind(item: Issue) {
        this.issue = item
        itemBinding.title.text = item.title
        itemBinding.body.text = item.body?.substring(0,
            item.body.length.coerceAtMost(200)
        )
        itemBinding.username.text="- "+item.user?.login
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val output = formatter.format(parser.parse(item.updated_at))
        itemBinding.updatedAt.text= output
        Glide.with(itemBinding.root)
            .load(item.user?.avatar_url)
            .transform(CircleCrop())
            .into(itemBinding.image)
    }

    override fun onClick(v: View?) {
        Data(info = issue.body,id=issue.number).let { listener.onClickedIssue(it) }
    }
}

