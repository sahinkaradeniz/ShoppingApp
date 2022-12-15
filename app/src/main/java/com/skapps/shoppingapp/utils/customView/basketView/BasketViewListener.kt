package com.skapps.shoppingapp.utils.customView.basketView

import com.skapps.shoppingapp.data.model.Product

interface BasketViewListener {
    fun addButtonClickListener(product: Product)
    fun deleteButtonClickListener(product: Product)
}