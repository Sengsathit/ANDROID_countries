package kodizfun.countries.layer_presentation.view.util

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kodizfun.countries.R

/**
 * Extension for loading images with Glide in ImageView
 */
fun ImageView.loadImage(uri: String?) {
    val options = RequestOptions().error(R.mipmap.ic_launcher_round)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}