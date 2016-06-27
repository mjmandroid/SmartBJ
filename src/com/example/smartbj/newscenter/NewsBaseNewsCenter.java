package com.example.smartbj.newscenter;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartbj.R;
import com.example.smartbj.domain.NewsCenterEntity.NewsData.ViewTagData;
import com.example.smartbj.newstpi.TPINewsNewscenterPager;
import com.example.smartbj.ui.MainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.TitlePageIndicator;

/**
 * @author mjm
 * @创建时间:2016-6-19 下午9:39:10
 * @描述信息:TODO
 * 
 */
public class NewsBaseNewsCenter extends BaseNewsCenter {
	@ViewInject(R.id.vp_newscenter)
	private ViewPager viewPager;
	@ViewInject(R.id.indictor)
	private TabPageIndicator tabPageIndicator;
	private List<ViewTagData> childrens;
	private MyAdapter adapter;
	public NewsBaseNewsCenter(MainActivity context, List<ViewTagData> childrens) {
		super(context);
		this.childrens = childrens;
	}

	@Override
	public View initViews() {
		View view = View.inflate(context, R.layout.newscenterpage_content,null);
		ViewUtils.inject(this, view);
		return view;
	}
	@Override
	public void initData() {
		super.initData();
		adapter = new MyAdapter();
		viewPager.setAdapter(adapter);
		tabPageIndicator.setViewPager(viewPager);
	}
	
	@Override
	public void initEvents() {
		super.initEvents();
		tabPageIndicator.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				SlidingMenu slidingMenu = context.getSlidingMenu();
				if(position == 0) {
					//可滑动左侧菜单
					slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
				}else {
					slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
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
	
	private class MyAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return childrens.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			// TODO Auto-generated method stub
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
//			TextView tv = new TextView(context);
//			tv.setText(childrens.get(position).title);
//			tv.setGravity(Gravity.CENTER);
//			container.addView(tv);
//			return tv;
			TPINewsNewscenterPager pager = new TPINewsNewscenterPager(context,childrens.get(position));
			View view = pager.getRoot();
			container.addView(view);
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
		
		/* (non-Javadoc)
		 * @see 也签的数据
		 */
		@Override
		public CharSequence getPageTitle(int position) {
			return childrens.get(position).title;
		}
	}
	@OnClick(R.id.ib_next)
	public void next(View view){
		viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
	}
}

