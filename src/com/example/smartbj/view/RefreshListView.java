package com.example.smartbj.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.smartbj.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * @author created by mjm
 * @创建时间:2016-6-27 下午11:32:48
 * @描述信息:TODO
 * 
 */
public class RefreshListView extends ListView{

	private View footView;
	private LinearLayout headView;
	private LinearLayout rootHeadView;
	private float downY = -1;
	private int hight;
	private View luoboTu;
	private int listviewInScroonY = 0;
	private final int PULL_DWON = 1;//下拉刷新
	private final int RELEASE_STATE = 2;//松开
	private final int REFRESH_STATE = 3;//正在刷新
	private int currentState = PULL_DWON;
	private TextView tv_state;
	private TextView tv_time;
	private ImageView iv_arrow;
	private ProgressBar pb_loading;
	private RotateAnimation up_ra;
	private RotateAnimation dwon_ra;
	private OnRefreshDataListenter listener;
	private boolean isLoadingMore;
	private int height2;
	public RefreshListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public RefreshListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView();
		
	}

	public RefreshListView(Context context) {
		super(context);
		initView();
	}

	private void initView() {
		initHeadView();
		initFootView();
		initAnimation();
		initEvents();
	}

	private void initEvents() {
		setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				if (getLastVisiblePosition() == getAdapter().getCount() - 1 && !isLoadingMore) {
					footView.setPadding(0, 0, 0, 0);
					setSelection(getAdapter().getCount());
					isLoadingMore = true;
					if (listener != null) {
						listener.loadMore();
					}
				}
				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				
				
			}
		});
		
	}

	private void initHeadView() {
		headView = (LinearLayout) View.inflate(getContext(), R.layout.headview, null);
		rootHeadView = (LinearLayout) headView.findViewById(R.id.ll_root_head);
		tv_state = (TextView) headView.findViewById(R.id.tv_listview_head_state_dsr);
		tv_time = (TextView) headView.findViewById(R.id.tv_listview_head_time);
		iv_arrow = (ImageView) headView.findViewById(R.id.iv_arrow);
		pb_loading = (ProgressBar) headView.findViewById(R.id.pb_loading);
		//测量高度
		rootHeadView.measure(0, 0);
		hight = rootHeadView.getMeasuredHeight();
		rootHeadView.setPadding(0, -hight, 0, 0);
		this.addHeaderView(headView);
	}

	private void initFootView() {
		footView = View.inflate(getContext(), R.layout.listview_footview, null);
		footView.measure(0, 0);
		height2 = footView.getMeasuredHeight();
		footView.setPadding(0, -height2, 0, 0);
		this.addFooterView(footView);
	}
	/**
	 * 轮播图
	 */
	public void addView(View v) {
		luoboTu = v;
		headView.addView(v);
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downY = ev.getY();
			System.out.println(downY+"ssss");
			break;
		case MotionEvent.ACTION_MOVE:
			if(downY == -1) {
				downY = ev.getY();
			}
			float moveY = ev.getY();
			
			int[] location = new int[2];
			if (listviewInScroonY == 0) {
				getLocationOnScreen(location);
				listviewInScroonY = location[1];
			}
			
			int[] location1 = new int[2];
			luoboTu.getLocationOnScreen(location1);
			if (location1[1] < listviewInScroonY) {
				//轮播图没有完全显示，相应ListView事件
				break;
			}
			if (currentState == REFRESH_STATE) {
				break;
			}
			float dY = moveY - downY;
			if (dY > 0 && getFirstVisiblePosition() == 0) {
				float scroY  =  -hight + dY;//当前paddingtop值
				if (scroY < 0 && currentState != PULL_DWON) {
					//下拉刷新
					currentState = PULL_DWON;
					refreshState();
				} else if (scroY > 0 && currentState != RELEASE_STATE) {
					//松开刷新
					currentState = RELEASE_STATE;
					refreshState();
				}
				rootHeadView.setPadding(0, (int) scroY, 0, 0);
				
				return true;
			}
			
			break;
		case MotionEvent.ACTION_UP:
			downY = -1;
			if (currentState == PULL_DWON) {
				rootHeadView.setPadding(0, -hight, 0, 0);
			} else if (currentState == RELEASE_STATE) {//正在刷新
				rootHeadView.setPadding(0, 0, 0, 0);
				currentState = REFRESH_STATE;
				refreshState();
				if (listener != null) {
					listener.refreshData();
				}
			}
			
			break;
			
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	private void initAnimation() {
		up_ra = new RotateAnimation(0, -180, 
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		up_ra.setDuration(500);
		up_ra.setFillAfter(true);
		
		dwon_ra = new RotateAnimation(-180, -360, 
				Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		dwon_ra.setDuration(500);
		dwon_ra.setFillAfter(true);
	}
	
	/**
	 * 刷新完成
	 */
	public void refreshFinish() {
		if (isLoadingMore) {
			isLoadingMore = false;
			footView.setPadding(0, -height2, 0, 0);
		} else {
			tv_state.setText("下拉刷新");
			iv_arrow.setVisibility(View.VISIBLE);
			pb_loading.setVisibility(View.INVISIBLE);
			tv_time.setText(getCurrentTime());
			rootHeadView.setPadding(0, -hight, 0, 0);
			currentState = PULL_DWON;
		}
		
	}
	public void setOnRefreshDataListenter(OnRefreshDataListenter listenter) {
		this.listener = listenter;
	}
	public interface OnRefreshDataListenter {
		void refreshData();
		void loadMore();
	}
	@SuppressLint("SimpleDateFormat")
	private String getCurrentTime(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(new Date());
	}
	private void refreshState() {
		switch (currentState) {
		case PULL_DWON:
			tv_state.setText("下拉刷新");
			iv_arrow.startAnimation(dwon_ra);
			break;
		case RELEASE_STATE:
			tv_state.setText("松开刷新");
			iv_arrow.startAnimation(up_ra);
			break;
		case REFRESH_STATE:
			iv_arrow.clearAnimation();
			iv_arrow.setVisibility(View.INVISIBLE);
			pb_loading.setVisibility(View.VISIBLE);
			tv_state.setText("正在刷新");
//			tv_time.setText(new Sim);
			break;
		default:
			break;
		}
		
	}
}
