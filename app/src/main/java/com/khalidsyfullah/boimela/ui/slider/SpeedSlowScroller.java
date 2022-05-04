package com.khalidsyfullah.boimela.ui.slider;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class SpeedSlowScroller extends Scroller {

    private int mDuration = 2500;

    public SpeedSlowScroller(Context context) {
        super(context);
    }

    public SpeedSlowScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    public SpeedSlowScroller(Context context, Interpolator interpolator, boolean flywheel) {
        super(context, interpolator, flywheel);
    }
}

