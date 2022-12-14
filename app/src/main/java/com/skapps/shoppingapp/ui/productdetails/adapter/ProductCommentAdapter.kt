package com.skapps.shoppingapp.ui.productdetails.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.databinding.RowProductCommentBinding
import com.skapps.shoppingapp.data.model.Comment
import com.skapps.shoppingapp.utils.averatingformat1F

class ProductCommentAdapter(val type:Int,val commentList:List<Comment>,private val onItemClick: (comment:Comment) -> Unit): RecyclerView.Adapter<ProductCommentAdapter.CommentViewHolder>() {
    class CommentViewHolder(val binding:RowProductCommentBinding):RecyclerView.ViewHolder(binding.root){
            fun bind(comment: Comment){
                binding.textProductComment.text=comment.content
                binding.textRatingComment.text=comment.rating?.averatingformat1F()
                binding.textUsername.text=comment.header
            }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val rcvComment=RowProductCommentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CommentViewHolder(rcvComment,)
    }
    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(commentList.get(position))
        holder.itemView.setOnClickListener {
            onItemClick(commentList.get(position))
        }
    }
    override fun getItemCount(): Int {
        if (type==1){
            if (commentList.size>4){
                return 4
            }else{
                return commentList.size
            }
        }else{
            return commentList.size
        }
    }
}