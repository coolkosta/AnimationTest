package com.coolkosta.animationtest

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView

class ObjectAnimatorActivity : AppCompatActivity() {
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_animator)
        imageView = findViewById(R.id._3dImageView)
        imageView.setOnClickListener {
            ObjectAnimator.ofFloat(it, View.ROTATION_X, 0f, 360f)
                .apply {
                    interpolator = LinearInterpolator()
                    duration = 1500
                    addListener(object : AnimatorListener {
                        override fun onAnimationStart(p0: Animator?) {

                        }

                        override fun onAnimationEnd(p0: Animator?) {
                            it.visibility = View.GONE
                        }

                        override fun onAnimationCancel(p0: Animator?) {

                        }

                        override fun onAnimationRepeat(p0: Animator?) {

                        }
                    })
                    start()
                }
        }

        findViewById<ImageView>(R.id.timeImageView).setOnClickListener {
            it.animate()
                .scaleXBy(10f)
                .scaleYBy(10f)
                .setDuration(600)
                .setInterpolator(LinearInterpolator())
                .start()


        }

    }
}