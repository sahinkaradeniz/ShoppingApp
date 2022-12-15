package com.skapps.shoppingapp.ui.cart.adapter

private fun addOnePiece(piece:Int):Int{
    piece.plus(1)
    return piece
}

private fun deleteOnePiece(piece:Int):Int{
    if (piece>0){
        piece.minus(1)
        return piece
    }else{
        return piece
    }
}


