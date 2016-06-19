package com.example.smartbj.ui;

import com.example.smartbj.R;
import com.example.smartbj.view.LeftMenuFragment;
import com.example.smartbj.view.MainContentFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;

/**
 * @author mjm
 * @创建时间:2016-6-13 下午11:14:12
 * @描述信息:TODO
 * 
 */
public class MainActivity extends SlidingFragmentActivity {
	private static final String LEFT_MENU = "left";
	private static final String MAIN_MENU = "main";
	private FragmentManager fragmentManager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		initViews();
		initData();
	}

	private void initData() {
		fragmentManager = getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.fl_left_menu, new LeftMenuFragment(),
				LEFT_MENU);
		transaction.replace(R.id.fl_main_menu, new MainContentFragment(),
				MAIN_MENU);
		transaction.commit();
	}

	private void initViews() {
		setContentView(R.layout.fragment_main_layoout);
		setBehindContentView(R.layout.fragment_left_layoout);
		SlidingMenu menu = getSlidingMenu();
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setBehindOffset(300);

	}
}
