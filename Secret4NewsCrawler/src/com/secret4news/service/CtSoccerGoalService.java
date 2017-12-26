package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtSoccerGoalService extends BaseService {

	private static final String NAME = "Goal.com";
	private static final String HOST = "http://m.goal.com";
	private static final String URL = "http://m.goal.com/s/kr/archive";
	
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
			Elements es = doc.getElementsByTag("article");
			
			for(Element el:es) {
				title = el.getElementsByAttributeValue("class", "title").text();
				link = el.getElementsByTag("a").attr("href");
								
				if(title.length() > 0 && link.length() > 0) {					
					link = HOST + link;
					
					addContent(title, link);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
