package com.itheima.circlepageindicator;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by Leon on 2017/6/22.
 */

public class CirclePageIndicator extends View {

    /**
     * 点的半径
     */
    private int mDotRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
    private int mDotGap = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());


    private ViewPager mViewPager;
    private Paint mDotPaint;


    public CirclePageIndicator(Context context) {
        this(context, null);
    }

    public CirclePageIndicator(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mDotPaint = new Paint();
        mDotPaint.setAntiAlias(true);
        mDotPaint.setColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = mViewPager.getAdapter().getCount();
        int width =  2 * mDotRadius * count + (count - 1) * mDotGap;
        int height = 2 * mDotRadius;
        setMeasuredDimension(width, height);
    }

    public void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
            float cx = mDotRadius + i * (mDotGap + 2 *  mDotRadius);
            float cy = mDotRadius;
            canvas.drawCircle(cx, cy, mDotRadius, mDotPaint);
        }
    }
}
