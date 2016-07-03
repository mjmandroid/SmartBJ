package com.example.smartbj.newstpi;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartbj.R;
import com.example.smartbj.basepage.GovPage;
import com.example.smartbj.basepage.HomePage;
import com.example.smartbj.domain.NewsCenterEntity.NewsData.ViewTagData;
import com.example.smartbj.domain.TpiNewsCenterEntity;
import com.example.smartbj.domain.TpiNewsCenterEntity.Data.News;
import com.example.smartbj.domain.TpiNewsCenterEntity.Data.Topnews;
import com.example.smartbj.tools.DensityUtils;
import com.example.smartbj.tools.MyConstants;
import com.example.smartbj.tools.SPtools;
import com.example.smartbj.ui.MainActivity;
import com.example.smartbj.view.RefreshListView;
import com.example.smartbj.view.RefreshListView.OnRefreshDataListenter;
import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * @author created by mjm
 * @创建时间:2016-6-21 下午9:25:37
 * @描述信息:TODO
 * 
 */
public class TPINewsNewscenterPager {
	private MainActivity mainActivity;
	private View root;
	private ViewTagData data;
	private Handler mHandler = new Handler();
	private LunboTask lunboTask = new LunboTask();
	@ViewInject(R.id.vp_tpi_news_centent)
	private ViewPager viewPager;
	
	@ViewInject(R.id.ll_news_centent)
	private LinearLayout llLayoutPoints;
	
	@ViewInject(R.id.listview_news_centent)
	private RefreshListView listView;
	
	@ViewInject(R.id.tv_content)
	private TextView tv_content;
	private Gson gson;
	private List<Topnews> topnews = new ArrayList<Topnews>();
	private LunBoAdapter lunBoAdapter;
	private BitmapUtils bitmapUtils;
	private int selectIndex;
	private List<News> news  = new ArrayList<TpiNewsCenterEntity.Data.News>();
	private NewsAdapter newsAdapter;
	private boolean isRerfsh = false;
	
	
	public View getRoot() {
		return root;
	}
	
	public TPINewsNewscenterPager(MainActivity mainActivity, ViewTagData viewTagData){
		this.mainActivity = mainActivity;
		this.data = viewTagData;
		initViews();
		initData();
		initEvents();
		bitmapUtils = new BitmapUtils(mainActivity);
		bitmapUtils.configDefaultBitmapConfig(Config.ARGB_4444);
	}
	private void initEvents() {
		viewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				selectIndex = position;
				setDesMsg(position);
				
				
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub
				
			}
		});
		listView.setOnRefreshDataListenter(new OnRefreshDataListenter() {
			
			@Override
			public void refreshData() {
				isRerfsh = true;
				getDataFromNetWork();
			}

			@Override
			public void loadMore() {
				
				
			}
		});
	}
	protected void loopView() {
		mHandler.removeCallbacksAndMessages(null);//清空原来的消息
		mHandler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % viewPager.getAdapter().getCount());
				mHandler.postDelayed(this, 1000);
			}
		}, 1000);
		
	}
	
	private class LunboTask extends Handler implements Runnable {

		@Override
		public void run() {
			viewPager.setCurrentItem((viewPager.getCurrentItem() + 1) % viewPager.getAdapter().getCount());
			postDelayed(this, 1500);
			
		}
		
		public void start() {
			stop();
			postDelayed(this, 1500);
		}
		public void stop() {
			removeCallbacksAndMessages(null);
		}
	}
	
	private void initData() {
		lunBoAdapter = new LunBoAdapter();
		viewPager.setAdapter(lunBoAdapter);
		newsAdapter = new NewsAdapter();
		listView.setAdapter(newsAdapter);
		String jsonnString = SPtools.getString(mainActivity, MyConstants.SERVERURL, "");
		if(!TextUtils.isEmpty(jsonnString)){
			TpiNewsCenterEntity entity = parserJsonData(jsonnString);
			//处理数据
			processData(entity);
		}else {
			getDataFromNetWork();
		}
		
	}
	private void processData(TpiNewsCenterEntity entity){
		
		//轮播图 
		setLunBo(entity);
		initPoints();
		
		setDesMsg(selectIndex);
//		loopView();
		lunboTask.start();
		
		setListViewData(entity);
	}
	private void setListViewData(TpiNewsCenterEntity entity) {
		news  = entity.data.news;
		newsAdapter.notifyDataSetChanged();
	}

	private void setDesMsg(int index) {
		tv_content.setText(topnews.get(index).title);
		for (int i = 0; i < topnews.size(); i++) {
			llLayoutPoints.getChildAt(i).setEnabled(i == index);
		}
		
	}

	private void initPoints() {
		llLayoutPoints.removeAllViews();
		for (int i = 0; i < lunBoAdapter.getCount(); i++) {
			View point = new View(mainActivity);
			point.setBackgroundResource(R.drawable.point_select);
			point.setEnabled(false);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtils.dip2px(mainActivity, 5),
					DensityUtils.dip2px(mainActivity, 5));
			params.leftMargin = 10;
			point.setLayoutParams(params);
			llLayoutPoints.addView(point);
		}
		
	}

	private void setLunBo(TpiNewsCenterEntity entity) {
		topnews = entity.data.topnews;
		lunBoAdapter.notifyDataSetChanged();
		
	}
	
	private class NewsAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return news.size();
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
			ViewHolder viewHolder = null;
			if (convertView == null) {
				viewHolder = new ViewHolder();
				convertView = View.inflate(mainActivity, R.layout.tpi_news_listvie_item, null);
				convertView.setTag(viewHolder);
				viewHolder.iv_newspic = (ImageView) convertView.findViewById(R.id.iv_item);
				viewHolder.iv_icon = (ImageView) convertView.findViewById(R.id.iv_commount);
				viewHolder.tv_title = (TextView) convertView.findViewById(R.id.tv_listView_item_title);
				viewHolder.tv_time = (TextView) convertView.findViewById(R.id.tv_listView_item_time);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}
			News newsEntity = news.get(position);
			viewHolder.tv_title.setText(newsEntity.title);
			viewHolder.tv_time.setText(newsEntity.pubdate);
			String url = newsEntity.listimage.replace("10.0.2.2", "192.168.253.1");
			bitmapUtils.display(viewHolder.iv_newspic, url);
			return convertView;
		}
		
	}
	private class ViewHolder {
		ImageView iv_newspic,iv_icon;
		TextView tv_title,tv_time;
	}
	private class LunBoAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return topnews.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView imageView = new ImageView(mainActivity);
			imageView.setImageResource(R.drawable.home_scroll_default);
			Topnews topnews2 = topnews.get(position);
			String url = topnews2.topimage;
			String u = url.replace("10.0.2.2", "192.168.253.1");
			bitmapUtils.display(imageView, u);
			container.addView(imageView);
			imageView.setOnTouchListener(new OnTouchListener() {
				
				@Override
				public boolean onTouch(View v, MotionEvent event) {
					switch (event.getAction()) {
					case MotionEvent.ACTION_MOVE:
						
					case MotionEvent.ACTION_DOWN:
						lunboTask.stop();
						break;
					case MotionEvent.ACTION_UP:
					case MotionEvent.ACTION_CANCEL:
						lunboTask.start();
						break;

					default:
						break;
					}
					return true;
				}
			});
			return imageView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
		
		
	}

	private void getDataFromNetWork() {
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.send(HttpMethod.GET, "http://123.135.128.105:8080/zhbj"+data.url, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String jsonString = responseInfo.result;
				//保存到本地
				SPtools.setString(mainActivity, MyConstants.SERVERURL, jsonString);
				TpiNewsCenterEntity jsonData = parserJsonData(jsonString);
				processData(jsonData);
				if (isRerfsh) {
					listView.refreshFinish();
				}
				
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(mainActivity, "网络请求失败", 0).show();
				if (isRerfsh) {
					listView.refreshFinish();
				}
			}
		});
		
	}

	private void initViews() {
		root = View.inflate(mainActivity, R.layout.tpi_news_centent, null);
		ViewUtils.inject(this, root);
		View headView = View.inflate(mainActivity, R.layout.viewpager_headview, null);
		ViewUtils.inject(this, headView);
		listView.addView(headView);
	}
	private TpiNewsCenterEntity parserJsonData(String jsonString){
		if(gson == null){
			gson = new Gson();
		}
		TpiNewsCenterEntity entity = gson.fromJson(jsonString, TpiNewsCenterEntity.class);
		return entity;
	}
}
