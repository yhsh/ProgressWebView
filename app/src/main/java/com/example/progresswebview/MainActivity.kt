package com.example.progresswebview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvJava.setOnClickListener(this);
        tvKotlin.setOnClickListener(this);
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.tvJava -> goToJava()
            R.id.tvKotlin -> goToKotlin()
        }
    }

    /**
     * 跳转Kotlin版本
     */
    private fun goToKotlin() {
        startActivity(Intent(this, KotlinWebViewActivity::class.java))
    }

    /**
     * 跳转Java版本
     */
    private fun goToJava() {
        startActivity(Intent(this, JavaWebViewActivity::class.java))
    }
}