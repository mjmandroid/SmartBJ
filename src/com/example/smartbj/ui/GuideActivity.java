package com.example.smartbj.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.smartbj.R;

/**
 * @author mjm
 * @创建时间:2016-6-12 下午10:36:05
 * @描述信息:TODO
 * 
 */
public class GuideActivity extends Activity{
	private ViewPager viewPager;
	private LinearLayout ll_points;
	private View view_red;
	private Button btn_tiya;
	private List<ImageView> list;
	private MyAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		initViews();
		initData();
		initEvents();
	}

	private void initEvents() {
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				if(position == list.size() - 1){
					btn_tiya.setVisibility(View.VISIBLE);
				} else{
					btn_tiya.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				
			}
		});
	}

	private void initData() {
		int[] pics = new int[]{R.drawable.guide_1,R.drawable.guide_2,R.drawable.guide_3};
		list = new ArrayList<ImageView>();
		for (int i = 0; i < pics.length; i++) {
			ImageView iv = new ImageView(this);
			iv.setImageResource(pics[i]);
			iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
			list.add(iv);
		}
		adapter = new MyAdapter();
		viewPager.setAdapter(adapter);
	}

	private void initViews() {
		viewPager = (ViewPager) findViewById(R.id.vp_guide);
		ll_points = (LinearLayout) findViewById(R.id.ll_guide_points);
		view_red = findViewById(R.id.view_guide_red);
		btn_tiya = (Button) findViewById(R.id.btn_tiyan);
	}
	private class MyAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView view = list.get(position);
			container.addView(view);
			return view;
		}

		
		
	}
}
