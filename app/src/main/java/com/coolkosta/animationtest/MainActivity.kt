package com.coolkosta.animationtest

import android.animation.AnimatorSet
import android.animation.ArgbEvaluator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.animation.BounceInterpolator
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.objectAnimatorButton).setOnClickListener {
            val intent = Intent(this, ObjectAnimatorActivity::class.java)
            startActivity(intent)
        }
        findViewById<ImageView>(R.id.imageView).setOnClickListener {
            startAnimate()
        }
    }

    private fun startAnimate() {
        val rotationXHolder = PropertyValuesHolder.ofFloat("rotationX", 0f, -300f)
        val colorHolder = PropertyValuesHolder.ofObject(
            "color", ArgbEvaluator(),
            Color.GREEN,
            Color.BLUE,
            Color.RED
        )
        val xAnimatorAndColor =
            ValueAnimator.ofPropertyValuesHolder(rotationXHolder, colorHolder).apply {
                interpolator = BounceInterpolator()
                duration = 2000
                addUpdateListener {
                    val valueAnimateRotation = it.getAnimatedValue("rotationX") as Float
                    val valueColor = it.getAnimatedValue("color") as Int
                    findViewById<ImageView>(R.id.imageView).rotationX = valueAnimateRotation
                    findViewById<ImageView>(R.id.imageView).setColorFilter(valueColor)
                }

            }

        val yAnimator = ValueAnimator.ofFloat(0f, -500f).apply {
            interpolator = LinearInterpolator()
            duration = 2000
            addUpdateListener {
                val valueAnimationY = it.animatedValue as Float
                findViewById<ImageView>(R.id.imageView).translationY = valueAnimationY
            }

        }

        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(yAnimator, xAnimatorAndColor)
        animatorSet.start()

    }
}
