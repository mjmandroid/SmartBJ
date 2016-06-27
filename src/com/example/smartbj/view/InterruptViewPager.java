package com.example.smartbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author created by mjm
 * @创建时间:2016-6-24 下午9:33:13
 * @描述信息:TODO
 * 
 */
public class InterruptViewPager extends ViewPager{

	private float downX;
	private float downY;
	private float moveX;
	private float moveY;
	public InterruptViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public InterruptViewPager(Context context) {
		super(context);
	}
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		getParent().requestDisallowInterceptTouchEvent(true);//父组件不拦截我touch的事件
		
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX = ev.getX();
			downY = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			moveX = ev.getX();
			moveY = ev.getY();
			float dx = moveX - downX;
			float dy = moveY - downY;
			if (Math.abs(dx) > Math.abs(dy)) {
				if (getCurrentItem() == 0 && dx > 0) {
					getParent().requestDisallowInterceptTouchEvent(false);
				} else if (getCurrentItem() == getAdapter().getCount() - 1 && dx < 0) {
					getParent().requestDisallowInterceptTouchEvent(false);
				} else {
					getParent().requestDisallowInterceptTouchEvent(true);
				}
			} else {
				getParent().requestDisallowInterceptTouchEvent(false);
			}
			break;
		default:
			break;
		}
		return super.dispatchTouchEvent(ev);
	}
}
