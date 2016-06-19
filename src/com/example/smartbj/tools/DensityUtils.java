package com.example.smartbj.tools;

import android.content.Context;

/**
 * @author mjm
 * @创建时间:2016-6-18 下午9:37:43
 * @描述信息:TODO
 * 
 */
public class DensityUtils {
	/**
	 * @param context
	 * @param dpValue
	 * @return  根据手机的分辨率dp转换成px
	 */
	public static int dip2px(Context context,float dpValue){
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}
	
	public static int px2dp(Context context,float pxValue){
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
}
