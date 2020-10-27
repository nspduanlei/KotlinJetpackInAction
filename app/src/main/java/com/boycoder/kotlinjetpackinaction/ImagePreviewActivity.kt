package com.boycoder.kotlinjetpackinaction

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_image_preview.*

/**
 *
 * @Description:
 * @Author:         duanlei
 * @CreateDate:     2020/10/26 17:28
 */

class ImagePreviewActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_preview)

        val intent = intent;
        val url = intent.getStringExtra(EXTRA_PHOTO);
        if (!TextUtils.isEmpty(url)) {
            Glide.with(this).load(url).into(imagePreview);
        }

    }
}