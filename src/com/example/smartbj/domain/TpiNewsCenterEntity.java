package com.example.smartbj.domain;

import java.util.List;

/**
 * @author created by mjm
 * @创建时间:2016-6-21 下午10:03:36
 * @描述信息:TODO
 * 
 */
public class TpiNewsCenterEntity {
	public Data data;
	public int retcode;
	public class Data{
		public String countcommenturl;
		public String more;
		public String title;
		public List<News> news;
		public List<Topic> topic;
		public List<Topnews> topnews;
		public class News{
			public String comment;
			public String commentlist;
			public String commenturl;
			public String id;
			public String listimage;
			public String pubdate;
			public String title;
			public String type;
			public String url;
		}
		public class Topic{
			public String description;
			public String id;
			public String listimage;
			public String sort;
			public String title;
			public String url;
		}
		public class Topnews{
			public String comment;
			public String commentlist;
			public String commenturl;
			public String id;
			public String pubdate;
			public String title;
			public String topimage;
			public String type;
			public String url;
		}
		
	}
}
