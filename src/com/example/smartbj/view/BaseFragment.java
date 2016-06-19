package com.example.smartbj.view;

import com.example.smartbj.ui.MainActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author mjm
 * @创建时间:2016-6-18 下午10:45:09
 * @描述信息:TODO
 * 
 */
public abstract class BaseFragment extends Fragment {
	protected MainActivity mainActivity;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainActivity = (MainActivity) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return initView();
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initData();
		initEvents();
	}
	
	public abstract View initView();
	public void initData(){
		
	}
	public void initEvents(){
		
	}
}
