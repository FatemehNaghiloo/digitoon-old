package com.example.digitoon.tech

import com.example.digitoon.customView.MyImageView
import com.facebook.drawee.view.SimpleDraweeView

class FrescoImageLoadingService :ImageLoadingService{
    override fun load(imageView: MyImageView, imageUrl: String) {
        if (imageView is SimpleDraweeView)
            imageView.setImageURI(imageUrl)
        else
            throw IllegalStateException("ImageView must be instance of SimpleDraweeView")
    }
}