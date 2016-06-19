package com.example.smartbj.view;

import java.util.ArrayList;
import java.util.List;

import com.example.smartbj.R;
import com.example.smartbj.basepage.BasePage;
import com.example.smartbj.basepage.GovPage;
import com.example.smartbj.basepage.HomePage;
import com.example.smartbj.basepage.NewsCenterPage;
import com.example.smartbj.basepage.SettingCenterPage;
import com.example.smartbj.basepage.SmartServerPage;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

/**
 * @author mjm
 * @创建时间:2016-6-18 下午10:57:45
 * @描述信息:TODO
 * 
 */
public class MainContentFragment extends BaseFragment {
	@ViewInject(R.id.vp)
	private MyViewPager viewPager;
	@ViewInject(R.id.rd)
	private RadioGroup radioGroup;
	private List<BasePage> pages = new ArrayList<BasePage>();
	private MyAdapter adapter;
	int selectIndex = 0;
	@Override
	public View initView() {
		View view = View.inflate(mainActivity, R.layout.fragment_main_layoout_view, null);
		ViewUtils.inject(this, view);
		view.findViewById(R.id.rb_home).performClick();
		return view;
	}
	@Override
	public void initData() {
		pages.add(new HomePage(mainActivity));
		pages.add(new NewsCenterPage(mainActivity));
		pages.add(new SmartServerPage(mainActivity));
		pages.add(new GovPage(mainActivity));
		pages.add(new SettingCenterPage(mainActivity));
		adapter = new MyAdapter();
		viewPager.setAdapter(adapter);
//		viewPager.setOffscreenPageLimit(2);//预加载,前后各两个
		switchPage();
	}
	@Override
	public void initEvents() {
		super.initEvents();
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				
				switch (checkedId) {
				case R.id.rb_home:
					selectIndex = 0;
					break;
				case R.id.rb_newscenter:
					selectIndex = 1;
					break;
				case R.id.rb_smaertserver:
					selectIndex = 2;
				break;
				case R.id.rb_govaffairs:
					selectIndex = 3;
					break;
				case R.id.rb_setttingcenter:
					selectIndex = 4;
					break;
				default:
					break;
				}
				switchPage();
			}
		});
	}
	protected void switchPage() {
		viewPager.setCurrentItem(selectIndex);
		if(selectIndex == 0 || selectIndex == pages.size() - 1){
			mainActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		} else{
			mainActivity.getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		}
	}
	private class MyAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return pages.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BasePage page = pages.get(position);
			View view = page.getRootView();
			container.addView(view);System.out.println("instantiateItem:"+position);
			page.initData();
			return view;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
//			System.out.println("destroyItem:"+position);
		}
		
		
	}
	
	public void leftMenuCliclSwichPage(int indext){
		BasePage page = pages.get(selectIndex);
		page.switchPage(indext);
	}
}
