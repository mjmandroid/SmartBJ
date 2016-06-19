package com.example.smartbj.basepage;

import com.example.smartbj.R;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * @author mjm
 * @创建时间:2016-6-19 上午10:19:59
 * @描述信息:TODO
 * 
 */
public class BasePage {
	public Context context;
	public View root;
	public ImageButton imageButton;
	public TextView tv_title;
	public FrameLayout fl;

	public BasePage(Context context) {
		this.context = context;
		initViews();
		
		initEvents();
	}

	public void initEvents() {
		final SlidingFragmentActivity activity = (SlidingFragmentActivity) context;
		imageButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				activity.getSlidingMenu().toggle();
			}
		});
		
	}

	public void initData() {
		
	}

	public void initViews() {
		root = View.inflate(context, R.layout.fragment_content_base_content, null);
		imageButton = (ImageButton) root.findViewById(R.id.ib);
		tv_title = (TextView) root.findViewById(R.id.tv_title);
		fl = (FrameLayout) root.findViewById(R.id.fl_content);
	}
	public View getRootView(){
		return root;
	}
	public void switchPage(int position){
		
	}
}
