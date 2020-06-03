package com.dragonforest.library.appstartrecord.main2

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.fragment.app.Fragment
import com.dragonforest.library.appstartrecord.R
import kotlinx.android.synthetic.main.activity_splash.*

/**
 *
 * create by DragonForest at 2020/6/3
 */
class SplashFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var v = LayoutInflater.from(context).inflate(R.layout.activity_splash,container,false)
        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    fun animateDismiss(listener: Animator.AnimatorListener){
        cl_splash.animate()
            .alpha(0f)
            .translationX(-cl_splash.width.toFloat())
            .setDuration(500)
            .setInterpolator(DecelerateInterpolator())
            .setListener(listener)
            .start()
    }
}