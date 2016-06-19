package com.example.smartbj.basepage;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * @author mjm
 * @创建时间:2016-6-19 上午10:32:37
 * @描述信息:TODO
 * 
 */
public class HomePage extends BasePage{

	public HomePage(Context context) {
		super(context);
	}
	@Override
	public void initData() {
		super.initData();
		//屏蔽菜单按钮
		imageButton.setVisibility(View.INVISIBLE);
		tv_title.setText("主页");
		TextView tv = new TextView(context);
		tv.setText("主页的内容");
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(25);
		fl.addView(tv);
	}

}
