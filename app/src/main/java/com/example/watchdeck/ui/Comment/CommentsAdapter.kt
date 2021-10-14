package com.example.watchdeck.ui.Comment

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.watchdeck.data.entities.Comment
import com.example.watchdeck.databinding.ItemCommentBinding

class CommentsAdapter() : RecyclerView.Adapter<IssueViewHolder>() {

    private val items = ArrayList<Comment>()

    fun setItems(items: ArrayList<Comment>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IssueViewHolder {
        val binding: ItemCommentBinding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IssueViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: IssueViewHolder, position: Int) = holder.bind(items[position],position)
}

class IssueViewHolder(private val itemBinding: ItemCommentBinding) : RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var comment: Comment


    @SuppressLint("SetTextI18n")
    fun bind(item: Comment, position: Int) {
        this.comment = item
        itemBinding.title.text="Comment "+(position+1)
        itemBinding.body.text = item.body
//        itemBinding.username.text="- "+item.user?.login
//        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
//        val formatter = SimpleDateFormat("yyyy-MM-dd")
//        val output = formatter.format(parser.parse(item.updated_at))
//        itemBinding.updatedAt.text= output
//        Glide.with(itemBinding.root)
//            .load(item.user?.avatar_url)
//            .transform(CircleCrop())
//            .into(itemBinding.image)
    }

    override fun onClick(v: View?) {
//        TODO("Not yet implemented")
    }
}