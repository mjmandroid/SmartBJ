package com.example.smartbj.view;

import com.example.smartbj.R;

import android.view.View;
import android.widget.TextView;

/**
 * @author mjm
 * @创建时间:2016-6-18 下午10:57:45
 * @描述信息:TODO
 * 
 */
public class MainContentFragment extends BaseFragment {

	@Override
	public View initView() {
		View view = View.inflate(mainActivity, R.layout.fragment_main_layoout_view, null);
		return view;
	}

}
