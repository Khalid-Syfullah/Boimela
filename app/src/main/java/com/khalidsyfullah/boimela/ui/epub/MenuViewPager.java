package com.khalidsyfullah.boimela.ui.epub;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class MenuViewPager extends ViewPager {

    private Boolean mAnimStarted = false;
    private MenuPagerAdapter mAdapter;

    public MenuViewPager(Context context) {
        super(context);
    }

    public MenuViewPager(Context context, AttributeSet attrs){
        super(context, attrs);
    }


//
//
//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//
//
////        int height = 0;
////        int childWidthSpec = MeasureSpec.makeMeasureSpec(
////                Math.max(0, MeasureSpec.getSize(widthMeasureSpec) -
////                        getPaddingLeft() - getPaddingRight()),
////                MeasureSpec.getMode(widthMeasureSpec)
////        );
////        for (int i = 0; i < getChildCount(); i++) {
////            View child = getChildAt(i);
////            child.measure(childWidthSpec, MeasureSpec.UNSPECIFIED);
////            int h = child.getMeasuredHeight();
////            if (h > height) height = h;
////        }
////
////        if (height != 0) {
////            heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
////        }
////
////        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//
//
////
////
////        if(!mAnimStarted && null != getAdapter()) {
////            int height = 0;
////            View child = ((MenuPagerAdapter) getAdapter()).getItem(getCurrentItem()).getView();
////            if (child != null) {
////                child.measure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
////                height = child.getMeasuredHeight();
////                Log.d("newHeight","oldHeight: "+height);
////                if (height < getMinimumHeight()) {
////                    height = getMinimumHeight();
////                }
////            }
////            else{
////                Log.d("newHeight","null child");
////
////            }
////
////
////            // Not the best place to put this animation, but it works pretty good.
////            int newHeight = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
////            Log.d("newHeight","newHeight: "+newHeight);
////            Log.d("newHeight","layout_height: "+getLayoutParams().height);
////            Log.d("newHeight","heightMeasureSpec: "+heightMeasureSpec);
////
////            if (getLayoutParams().height != 0 && heightMeasureSpec != newHeight) {
////
////                Log.d("newHeight","We are inside if");
////                final int targetHeight = height;
////                final int currentHeight = getLayoutParams().height;
////                final int heightChange = targetHeight - currentHeight;
////
////                Animation a = new Animation() {
////                    @Override
////                    protected void applyTransformation(float interpolatedTime, Transformation t) {
////                        if (interpolatedTime >= 1) {
////                            getLayoutParams().height = targetHeight;
////                        } else {
////                            int stepHeight = (int) (heightChange * interpolatedTime);
////                            getLayoutParams().height = currentHeight + stepHeight;
////                        }
////                        requestLayout();
////                    }
////
////                    @Override
////                    public boolean willChangeBounds() {
////                        return true;
////                    }
////                };
////
////                a.setAnimationListener(new Animation.AnimationListener() {
////                    @Override
////                    public void onAnimationStart(Animation animation) {
////                        mAnimStarted = true;
////                    }
////
////                    @Override
////                    public void onAnimationEnd(Animation animation) {
////                        mAnimStarted = false;
////                    }
////
////                    @Override
////                    public void onAnimationRepeat(Animation animation) {
////                    }
////                });
////
////                a.setDuration(1000);
////                startAnimation(a);
////                mAnimStarted = true;
////            } else {
////                heightMeasureSpec = newHeight;
////                Log.d("newHeight","We are inside else");
////
////            }
////        }
////
////        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }
}