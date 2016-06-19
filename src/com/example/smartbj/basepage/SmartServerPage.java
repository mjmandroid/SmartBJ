package com.example.smartbj.basepage;

import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

/**
 * @author mjm
 * @创建时间:2016-6-19 上午10:32:37
 * @描述信息:TODO
 * 
 */
public class SmartServerPage extends BasePage{

	public SmartServerPage(Context context) {
		super(context);
	}
	@Override
	public void initData() {
		super.initData();
		tv_title.setText("智慧服务");
		TextView tv = new TextView(context);
		tv.setText("智慧服务的内容");
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(25);
	}
}
