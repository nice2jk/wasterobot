package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtSoccerGoalService extends BaseService {

	private static final String NAME = "FM Korea";
	private static final String HOST = "https://www.fmkorea.com";
	private static final String URL = "https://www.fmkorea.com/index.php?mid=football_news&sort_index=pop&order_type=desc";
	
	private static final String ENCORDING = null;
	
	private static final String CATEGORY = "socc";
	
	public CtSoccerGoalService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;

		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValueContaining("class", "hotdeal_var8");
			
			for(Element el:es) {
				title = el.getElementsByTag("a").text();
				link = el.getElementsByTag("a").attr("href");
				
				if(title.length() > 0 && link.length() > 0) {
					title = title.substring(0, title.lastIndexOf("["));
					link = HOST + link;
					
					addContent(title, link);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
