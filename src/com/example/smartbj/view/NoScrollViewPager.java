package com.example.smartbj.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author mjm
 * @创建时间:2016-6-19 上午11:40:38
 * @描述信息:TODO
 * 
 */
public class NoScrollViewPager extends MyViewPager{

	private float downY;
	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public NoScrollViewPager(Context context) {
		super(context);
	}
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		/*switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downY = ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			float moveY = ev.getY();
			float dy = moveY - downY;
			if(dy > 0) {
				return super.onTouchEvent(ev);
			}
			break;
		case MotionEvent.ACTION_UP:
			
			break;
		default:
			break;
		}*/
		return false;
	}
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		
		return false;
	}
}
