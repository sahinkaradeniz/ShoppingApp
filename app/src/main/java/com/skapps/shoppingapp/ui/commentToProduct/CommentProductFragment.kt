package com.skapps.shoppingapp.ui.commentToProduct

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.skapps.shoppingapp.R

class CommentProductFragment : Fragment() {

    companion object {
        fun newInstance() = CommentProductFragment()
    }

    private lateinit var viewModel: CommentProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_comment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(CommentProductViewModel::class.java)
    }

}