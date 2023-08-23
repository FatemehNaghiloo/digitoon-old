package com.example.digitoon.tech

import com.example.digitoon.customView.MyImageView

interface ImageLoadingService {
    fun load(imageView: MyImageView, imageUrl: String)
}