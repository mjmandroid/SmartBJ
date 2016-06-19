package com.example.smartbj.basepage;

import java.util.ArrayList;
import java.util.List;

import android.R.menu;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartbj.domain.NewsCenterEntity;
import com.example.smartbj.newscenter.BaseNewsCenter;
import com.example.smartbj.newscenter.InteractBaseNewsCenter;
import com.example.smartbj.newscenter.NewsBaseNewsCenter;
import com.example.smartbj.newscenter.PhotosBaseNewsCenter;
import com.example.smartbj.newscenter.TopicBaseNewsCenter;
import com.example.smartbj.tools.MyConstants;
import com.example.smartbj.ui.MainActivity;
import com.example.smartbj.view.LeftMenuFragment;
import com.example.smartbj.view.LeftMenuFragment.SwitchPagerListener;
import com.example.smartbj.view.MainContentFragment;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * @author mjm
 * @创建时间:2016-6-19 上午10:32:37
 * @描述信息:TODO
 * 
 */
public class NewsCenterPage extends BasePage implements SwitchPagerListener{
	private List<BaseNewsCenter> newsCenters = new ArrayList<BaseNewsCenter>();
	private NewsCenterEntity entity;
	public NewsCenterPage(Context context) {
		super(context);
		MainActivity mainActivity = (MainActivity) context;
		LeftMenuFragment leftMenuFragment = (LeftMenuFragment) mainActivity.getLeftFragment();
		leftMenuFragment.setOnSwitchPagerListener(this);
	}
	@Override
	public void initData() {
		super.initData();
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, MyConstants.NEWSCENTERURL, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String jsonString = responseInfo.result;
				parserJson(jsonString);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(context, error.toString(), Toast.LENGTH_LONG).show();
			}
		});
		
	}
	protected void parserJson(String jsonString) {
		Gson gson = new Gson();
		entity = gson.fromJson(jsonString, NewsCenterEntity.class);
		MainActivity mainActivity = (MainActivity) context;
		LeftMenuFragment leftMenuFragment = (LeftMenuFragment) mainActivity.getLeftFragment();
		leftMenuFragment.setData(entity.data);
		
		for(NewsCenterEntity.NewsData data : entity.data){
			BaseNewsCenter newsCenter = null;
			switch (data.type) {
			case 1://新闻
				newsCenter = new NewsBaseNewsCenter(mainActivity);
				break;
			case 10://专题 
				newsCenter = new TopicBaseNewsCenter(mainActivity);
				break;
			case 2://组图
				newsCenter = new PhotosBaseNewsCenter(mainActivity);
				break;
			case 3://互动
				newsCenter = new InteractBaseNewsCenter(mainActivity);
				break;
			default:
				break;
			}
			newsCenters.add(newsCenter);
		}
		switchPage(0);//默认情况
	}
	/**
	 * @param position根据位置选择
	 */
	@Override
	public void switchPage(int position) {
		super.switchPage(position);
		BaseNewsCenter baseNewsCenter = newsCenters.get(position);
		View view = baseNewsCenter.getRoot();
		tv_title.setText(entity.data.get(position).title);
		fl.removeAllViews();
		fl.addView(view);
	}
	@Override
	public void onSwitchPage(int p) {
		switchPage(p);
		
	}
}
