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
public class SettingCenterPage extends BasePage{

	public SettingCenterPage(Context context) {
		super(context);
	}
	@Override
	public void initData() {
		super.initData();
		imageButton.setVisibility(View.GONE);
		tv_title.setText("设置中心");
		TextView tv = new TextView(context);
		tv.setText("设置中心的内容");
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(25);
	}
}
