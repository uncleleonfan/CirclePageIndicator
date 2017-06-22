package com.itheima.circlepageindicator;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private CirclePageIndicator mCirclePageIndicator;

    private int[] mDataList = {R.mipmap.a, R.mipmap.b, R.mipmap.c, R.mipmap.d};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp);
        mViewPager.setAdapter(mPagerAdapter);
        mCirclePageIndicator = (CirclePageIndicator) findViewById(R.id.indicator);
        mCirclePageIndicator.setViewPager(mViewPager);
    }

    private PagerAdapter mPagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return mDataList.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = new ImageView(MainActivity.this);
            imageView.setImageResource(mDataList[position]);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };
}
