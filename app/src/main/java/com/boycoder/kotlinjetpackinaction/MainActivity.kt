package com.boycoder.kotlinjetpackinaction

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.boycoder.kotlinjetpackinaction.entity.User
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

/**
 *
 * @Description:
 * @Author:         duanlei
 * @CreateDate:     2020/10/27 9:11
 */
class MainActivity : AppCompatActivity() {

    private val tag = "Main"

    lateinit var stringRequest: StringRequest
    private val requestQueue: RequestQueue by lazy(LazyThreadSafetyMode.NONE) {
         Volley.newRequestQueue(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        requestOnlineInfo()
    }

    private fun requestOnlineInfo() {
        val url = "https://api.github.com/users/JakeWharton"
        stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> { response ->
                    display(response)
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, error.message,
                            Toast.LENGTH_SHORT).show()
                })
        stringRequest.tag = tag
        requestQueue.add(stringRequest)
    }

    private fun display(response: String?) {
        if (response.isNullOrBlank()) {
            return
        }
        val user = Gson().fromJson(response,  User::class.java)
        user?.run {
            Glide.with(this@MainActivity).load("file:///android_asset/bless.gif").into(gif)
            Glide.with(this@MainActivity).load(avatar_url)
                    .apply(RequestOptions.circleCropTransform()).into(image)
            this@MainActivity.company.text = company
            username.text = name
            website.text = blog
            image.setOnClickListener {
                gotoImagePreviewActivity(this)
            }
        }
    }

    private fun gotoImagePreviewActivity(user: User) {
        val intent = Intent(this, ImagePreviewActivity::class.java)
        intent.putExtra(EXTRA_PHOTO, user.avatar_url)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        requestQueue.cancelAll(tag)
    }

}