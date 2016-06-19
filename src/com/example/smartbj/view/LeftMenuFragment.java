package com.example.smartbj.view;

import android.view.View;
import android.widget.TextView;

/**
 * @author mjm
 * @创建时间:2016-6-18 下午10:57:14
 * @描述信息:TODO
 * 
 */
public class LeftMenuFragment extends BaseFragment {

	@Override
	public View initView() {
		TextView tv = new TextView(mainActivity);
		tv.setText("left");
		return tv;
	}

}
