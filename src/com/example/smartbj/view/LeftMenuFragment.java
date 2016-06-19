package com.example.smartbj.view;

import java.util.List;

import com.example.smartbj.R;
import com.example.smartbj.domain.NewsCenterEntity.NewsData;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * @author mjm
 * @创建时间:2016-6-18 下午10:57:14
 * @描述信息:TODO
 * 
 */
public class LeftMenuFragment extends BaseFragment {
	private List<NewsData> data;
	private ListView listView;
	private MyAdapter adapter;
	private int index = 0;
	public interface SwitchPagerListener{
		void onSwitchPage(int p);
	}
	private SwitchPagerListener listener;
	public void setOnSwitchPagerListener(SwitchPagerListener listener){
		this.listener = listener;
	}
	@Override
	public View initView() {
		listView = new ListView(mainActivity);
		listView.setBackgroundColor(Color.BLACK);
		listView.setCacheColorHint(Color.TRANSPARENT);//选中拖动背景色为透明
		listView.setSelector(new ColorDrawable(Color.TRANSPARENT));//选中时颜色
		listView.setDividerHeight(0);
		listView.setPadding(0, 50, 0, 0);
		return listView;
	}
	@Override
	public void initData() {
		super.initData();
		adapter = new MyAdapter();
		listView.setAdapter(adapter);
	}
	public void setData(List<NewsData> data){
		this.data = data;
		adapter.notifyDataSetChanged();
	}
	@Override
	public void initEvents() {
		super.initEvents();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				index = position;
				adapter.notifyDataSetChanged();
//				MainContentFragment mainContentFragment = (MainContentFragment) mainActivity.getMainFragment();
//				mainContentFragment.leftMenuCliclSwichPage(position);
				if(listener != null){
					listener.onSwitchPage(position);
				}
				mainActivity.getSlidingMenu().toggle();
			}
		});
	}
	private class MyAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return data == null ? 0 : data.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null){
				convertView = View.inflate(mainActivity, R.layout.item_left_menu, null);
			}
			TextView tv = (TextView) convertView;
			tv.setText(data.get(position).title);
			tv.setEnabled(index == position);
			return tv;
		}
		
	}
}
