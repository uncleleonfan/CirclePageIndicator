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

    //点的半径
    private int mDotRadius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());
    //点与点的间隔
    private int mDotGap = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics());


    private ViewPager mViewPager;
    private Paint mDotPaint;

    private int mPosition;
    private float mPositionOffset;

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
        mViewPager.addOnPageChangeListener(mOnPageChangeListener);
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            mPosition = position;
            mPositionOffset = positionOffset;
            invalidate();
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        int dotDistance = mDotGap + 2 *  mDotRadius;//点与点之间圆心的距离
        //循环遍历不动点
        for (int i = 0; i < mViewPager.getAdapter().getCount(); i++) {
            float cx = mDotRadius + i * dotDistance;
            float cy = mDotRadius;
            mDotPaint.setColor(Color.WHITE);
            canvas.drawCircle(cx, cy, mDotRadius, mDotPaint);
        }
        //绘制动点
        mDotPaint.setColor(Color.RED);

        float mMoveCx = mDotRadius + mPosition * dotDistance + dotDistance * mPositionOffset;
        float mMoveCy = mDotRadius;
        canvas.drawCircle(mMoveCx, mMoveCy, mDotRadius, mDotPaint);
    }
}
