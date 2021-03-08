package com.kotdev.postcomments.helpers

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.kotdev.postcomments.R

object ItemAnimation {
     fun setAnimation(viewToAnimate: View, anim: Int) {
        val animation: Animation =
            AnimationUtils.loadAnimation(viewToAnimate.context, anim)
        viewToAnimate.startAnimation(animation)
    }
}
