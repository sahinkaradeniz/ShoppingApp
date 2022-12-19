package com.skapps.shoppingapp.utils.customView.basketView
import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.cardview.widget.CardView
import com.skapps.shoppingapp.R
import com.skapps.shoppingapp.databinding.CustomBasketViewBinding
import com.skapps.shoppingapp.utils.hide
import com.skapps.shoppingapp.utils.show

@SuppressLint("CustomViewStyleable")
class BasketView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0,
) : FrameLayout(context, attributeSet, defStyle){
    private val binding =CustomBasketViewBinding.inflate(LayoutInflater.from(context),this,true)
    private var piece:Int=0
    var listener:BasketViewListener? = null


    fun addButtonclickListener(addonclick:(view:View)->Unit) {
        binding.bvAddButton.setOnClickListener {
            addonclick.invoke(it)
        }
    }
    fun deleteButtonClickListener(deleteonclick: (view: View) -> Unit){
        binding.bvDeleteButton.setOnClickListener {
            deleteonclick.invoke(it)
        }
    }


    init {
        binding.root.id=View.generateViewId()
        context.obtainStyledAttributes(attributeSet, R.styleable.BasketView).apply {
            piece = getInt(R.styleable.BasketView_bv_updatePiece, 0)
            recycle()
            setPiece(piece)
        }
    }


    fun getPiece():Int{
        return piece
    }
    private fun addOnePiece():Int{
        piece += 1
        binding.bvPieceText.text=piece.toString()
        binding.bvDeleteButton.show()
        return piece
    }

    private fun deleteOnePiece():Int{
        if (piece>0){
            piece -= 1
        }
        binding.bvPieceText.text=piece.toString()
        return piece
    }
     fun setPiece(newPiece : Int):Int{
        piece=newPiece
        binding.bvPieceText.text=piece.toString()
        return piece
    }
}