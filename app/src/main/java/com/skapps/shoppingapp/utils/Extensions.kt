package com.skapps.shoppingapp.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import cn.pedant.SweetAlert.SweetAlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.skapps.shoppingapp.R
import es.dmoral.toasty.Toasty
import java.io.File



@SuppressLint("CheckResult")
infix fun Context.infoToast(message:String){
    Toasty.info(this,message, Toasty.LENGTH_SHORT).show()
}

@SuppressLint("CheckResult")
infix fun Context.toast(message:String){
    Toasty.normal(this,message, Toasty.LENGTH_SHORT).show()
}

@SuppressLint("CheckResult")
infix fun Context.warningToast(message:String){
    Toasty.warning(this,message, Toasty.LENGTH_SHORT).show()
}
@SuppressLint("CheckResult")
infix fun Context.succesToast(message:String){
  Toasty.success(this,message, Toasty.LENGTH_SHORT).show()
}
@SuppressLint("CheckResult")
fun Context.succesBasketToast(){
    Toasty.custom(this,"Ürün Sepete Eklendi",
        R.drawable.ic_outline_shopping_cart_24,R.color.colorPrimary, Toasty.LENGTH_SHORT,true,true).show()
}
@SuppressLint("CheckResult")
fun Context.succesFavoriteToast(){
    Toasty.custom(this,"Ürün Favorilere Eklendi",
        R.drawable.ic_outline_favorite_nav,R.color.colorPrimary, Toasty.LENGTH_SHORT,true,true).show()
}
fun NavController.safeNavigate(direction: NavDirections) {
    Log.d("safe", "Click happened")
    currentDestination?.getAction(direction.actionId)?.run {
        Log.d("safe", "Click Propagated")
        navigate(direction)
    }
}
fun Context.succesAlert(titleText:String,confirmText:String){
    SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
        .setTitleText(titleText)
        .setConfirmText(confirmText)
        .show()
}
fun Context.warningAlert(titleText:String,confirmText:String){
    SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
        .setTitleText(titleText)
        .setConfirmText(confirmText)
        .show()
}

fun Bitmap.makeSmallerBitmap(image: Bitmap, maximumSize: Int): Bitmap {
    var height = image.height
    var width = image.width
    val bitmapRatio :Double=width.toDouble()/height.toDouble()
    if(bitmapRatio > 1 ){
        //landSpace
        width=maximumSize
        val scaledHeight=width/bitmapRatio
        height = scaledHeight.toInt()
    }else{
        //potrait
        height=maximumSize
        val scaledWidht=height* bitmapRatio
        width=scaledWidht.toInt()
    }
    return Bitmap.createScaledBitmap(image,width,height,true)
}
fun getFileChooserIntentForImageAndPdf(): Intent {
    val mimeTypes = arrayOf("application/pdf")
    val intent = Intent(Intent.ACTION_GET_CONTENT,)
        .setType("application/pdf")
        .putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
    return intent
}
fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun ImageView.downloadFromUrl(url: String?, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
        .placeholder(progressDrawable)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)
}

fun placeholderProgressBar(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}
fun Number.convertPricetoTL():String{
    val p = "${this} TL"
    return p
}
fun ImageView.downloadImage( url:String?) {
    val imageUrl="http://10.0.14.243:8080/v1/image/$url"
    this.downloadFromUrl(imageUrl, placeholderProgressBar(this.context))
}
fun Activity.OrangechangeStatusBarColor(isLight: Boolean) {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor =getColor(R.color.splashColor)
    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = isLight
}fun Activity.DefaultchangeStatusBarColor(isLight: Boolean) {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor =getColor(R.color.grey)
    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = isLight
}


