package com.skapps.shoppingapp.ui.purchaseHistory.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skapps.shoppingapp.data.model.Product
import com.skapps.shoppingapp.data.model.PurchaseProduct
import com.skapps.shoppingapp.data.responce.ProductResponce
import com.skapps.shoppingapp.data.responce.PurchaseResponce
import com.skapps.shoppingapp.databinding.RowChildHistoryPurchaseBinding
import com.skapps.shoppingapp.utils.convertPricetoTL

class HistoryChildAdapter(private val productList: List<ProductResponce>,val onItemClick:(ProductResponce)-> Unit):RecyclerView.Adapter<HistoryChildAdapter.ChildViewHolder>() {
    class ChildViewHolder( val binding:RowChildHistoryPurchaseBinding):RecyclerView.ViewHolder(binding.root){
            fun bind(productResponce:ProductResponce){
                    binding.productNameHistory.text="Ürün kodu : ${productResponce.id}"
                    binding.productPriceHistory.text="Ürün fiyatı : ${productResponce.unitPrice?.convertPricetoTL()}"
                    binding.productQuantitiyHistory.text="Alınan Ürün Adedi : ${productResponce.quantity}"
                    binding.productQuantitiyHistory2.text="Satış Durumu : ${productResponce.status}"
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildViewHolder {
       val binding=RowChildHistoryPurchaseBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ChildViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChildViewHolder, position: Int) {
       holder.bind(productList.get(position))
       holder.binding.addCommentButtonHistory.setOnClickListener {
           onItemClick(productList.get(position))
       }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

}