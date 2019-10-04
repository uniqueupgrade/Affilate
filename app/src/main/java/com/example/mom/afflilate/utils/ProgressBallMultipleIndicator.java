package com.example.mom.afflilate.utils;

import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.animation.LinearInterpolator;

import java.util.ArrayList;

class ProgressBallMultipleIndicator extends Indicator {

    private float[] scaleFloats = new float[]{1, 1, 1, 1, 1, 1};
    private int[] alphaInts = new int[]{255, 255, 255, 255, 255, 255};

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float circleSpacing = 8;
        for (int i = 0; i < 6; i++) {
            paint.setAlpha(alphaInts[i]);
            canvas.scale(scaleFloats[i], scaleFloats[i], getWidth() / 2, getHeight() / 2);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2 - circleSpacing, paint);
        }
    }

    @Override
    public ArrayList<ValueAnimator> onCreateAnimators() {
        ArrayList<ValueAnimator> animators = new ArrayList<>();
        long[] delays = new long[]{0, 200, 400, 600, 800, 100};
        for (int i = 0; i < 6; i++) {
            final int index = i;
            ValueAnimator scaleAnim = ValueAnimator.ofFloat(0, 1);
            scaleAnim.setInterpolator(new LinearInterpolator());
            scaleAnim.setDuration(2000);
            scaleAnim.setRepeatCount(-1);
            addUpdateListener(scaleAnim, new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    scaleFloats[index] = (float) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            scaleAnim.setStartDelay(delays[i]);

            ValueAnimator alphaAnim = ValueAnimator.ofInt(255, 0);
            alphaAnim.setInterpolator(new LinearInterpolator());
            alphaAnim.setDuration(2000);
            alphaAnim.setRepeatCount(-1);
            addUpdateListener(alphaAnim, new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    alphaInts[index] = (int) animation.getAnimatedValue();
                    postInvalidate();
                }
            });
            scaleAnim.setStartDelay(delays[i]);

            animators.add(scaleAnim);
            animators.add(alphaAnim);
        }
        return animators;
    }
}
