package com.kaiqi.love_family.view.dialog;

import android.view.View;

import com.nineoldandroids.animation.ObjectAnimator;
/**
 * 由下方动画展示dialog。
 * @author 李鑫    18290545819
 *
 */
public class RotateBottom extends BaseEffects {

	 @Override
	    protected void setupAnimation(View view) {
	        getAnimatorSet().playTogether(
	                ObjectAnimator.ofFloat(view, "rotationX",90, 0).setDuration(mDuration),
	                ObjectAnimator.ofFloat(view, "translationY", 300, 0).setDuration(mDuration),
	                ObjectAnimator.ofFloat(view, "alpha", 0, 1).setDuration(mDuration*3/2)

	        );
	    }

}
