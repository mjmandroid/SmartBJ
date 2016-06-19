package com.example.smartbj.newscenter;

import android.view.View;

import com.example.smartbj.ui.MainActivity;

/**
 * @author mjm
 * @创建时间:2016-6-19 下午9:35:24
 * @描述信息:TODO
 * 
 */
public abstract class BaseNewsCenter {
	protected MainActivity context;
	protected View root;
	public BaseNewsCenter(MainActivity context) {
		this.context = context;
		root = initViews();
		initEvents();
	}
	public abstract View initViews();
	
	public View getRoot() {
		return root;
	}
	public void initData(){
		
	}
	public void initEvents(){
		
	}
	
}
