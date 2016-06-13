package com.example.smartbj.tools;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author mjm
 * @创建时间:2016-6-12 下午5:58:47
 * @描述信息:TODO
 * 
 */
public class SPtools {
	/**
	 * @param key
	 * @param b
	 */
	public static void setBoolean(Context mContext,String key,boolean value){
		SharedPreferences sharedPreferences = mContext.getSharedPreferences(MyConstants.CINFIG, mContext.MODE_PRIVATE);
		sharedPreferences.edit().putBoolean(key, value).commit();
	}
	
	public static boolean getBoolean(Context mContext,String key,boolean defaultValue){
		SharedPreferences sharedPreferences = mContext.getSharedPreferences(MyConstants.CINFIG, mContext.MODE_PRIVATE);
		return sharedPreferences.getBoolean(key, defaultValue);
	}
}
