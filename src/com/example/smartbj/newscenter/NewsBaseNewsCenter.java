package com.example.smartbj.newscenter;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.smartbj.ui.MainActivity;

/**
 * @author mjm
 * @创建时间:2016-6-19 下午9:39:10
 * @描述信息:TODO
 * 
 */
public class NewsBaseNewsCenter extends BaseNewsCenter {

	public NewsBaseNewsCenter(MainActivity context) {
		super(context);
	}

	@Override
	public View initViews() {
		TextView tv = new TextView(context);
		tv.setText("新闻的内容");
		tv.setGravity(Gravity.CENTER);
		return tv;
	}

}

