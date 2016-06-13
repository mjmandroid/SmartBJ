package com.example.smartbj.ui;

import com.example.smartbj.R;
import com.example.smartbj.tools.MyConstants;
import com.example.smartbj.tools.SPtools;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

/**
 * @author mjm
 * @创建时间:2016-6-12 下午5:34:47
 * @描述信息:动画界面
 * 
 */
public class SplashActivity extends Activity implements AnimationListener{
	private ImageView imageView;
	private AnimationSet as;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initViews();
		startAnimation();
		initEvents();
	}

	/**
	 * 设置事件
	 */
	private void initEvents() {
		as.setAnimationListener(this);
	}

	/**
	 * 播放动画
	 * false 代表每种动画都采用各自的动画插入器
	 */
	private void startAnimation() {
		as = new AnimationSet(false);
		RotateAnimation ra = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, 
				Animation.RELATIVE_TO_SELF, 0.5f);
		ra.setDuration(2000);
		ra.setFillAfter(true);
		as.addAnimation(ra);
		
		AlphaAnimation aa = new AlphaAnimation(0, 1);
		aa.setDuration(2000);
		aa.setFillAfter(true);
		as.addAnimation(aa);
		
		ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, 
				Animation.RELATIVE_TO_SELF, 0.5f, 
				Animation.RELATIVE_TO_SELF, 0.5f);
		sa.setDuration(2000);
		sa.setFillAfter(true);
		as.addAnimation(sa);
		
		imageView.startAnimation(as);
		
	}

	private void initViews() {
		imageView = (ImageView) findViewById(R.id.iv_splash);
	}

	@Override
	public void onAnimationStart(Animation animation) {
		
	}

	@Override
	public void onAnimationEnd(Animation animation) {
		if(!SPtools.getBoolean(getApplicationContext(), MyConstants.ISSETUP, false)){
			Intent intent = new Intent(this, GuideActivity.class);
			startActivity(intent);
		} else{
			Intent intent = new Intent(this,MainActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public void onAnimationRepeat(Animation animation) {
		
	}
}
