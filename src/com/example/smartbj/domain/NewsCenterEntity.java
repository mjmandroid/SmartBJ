package com.example.smartbj.domain;

import java.util.List;

/**
 * @author mjm
 * @创建时间:2016-6-19 下午3:41:30
 * @描述信息:TODO
 * 
 */
public class NewsCenterEntity {
	public int retcode;

	public List<NewsData> data;// 新闻的数据

	public class NewsData {
		public List<ViewTagData> children;

		
		public class ViewTagData {
			public String id;
			public String title;
			public int type;
			public String url;
		}

		public String id;

		public String title;
		public int type;

		public String url;
		public String url1;

		public String dayurl;
		public String excurl;

		public String weekurl;
	}

	public List<String> extend;
}
