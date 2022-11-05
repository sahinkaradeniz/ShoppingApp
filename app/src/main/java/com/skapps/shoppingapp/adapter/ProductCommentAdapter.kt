package com.skapps.shoppingapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.databinding.RowProductCommentBinding
import com.skapps.shoppingapp.model.Comment

class ProductCommentAdapter(val commentList:ArrayList<Comment>): RecyclerView.Adapter<ProductCommentAdapter.CommentViewHolder>() {
    class CommentViewHolder(val binding:RowProductCommentBinding):RecyclerView.ViewHolder(binding.root){
            fun bind(comment: Comment){
                binding.textProductComment.text=comment.comment
                binding.textRatingComment.text=comment.rating
                binding.textUsername.text=comment.username
            }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val rcvComment=RowProductCommentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentViewHolder(rcvComment)
    }
    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(commentList.get(position))
    }
    override fun getItemCount(): Int {
        if (commentList.size>4){
            return 4
        }else{
            return commentList.size
        }
    }
}