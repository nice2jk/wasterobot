package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtShopClienService extends BaseService {

	private static final String NAME = "Clien Market";
	private static final String HOST = "https://www.clien.net";
	private static final String URL = "https://www.clien.net/service/board/jirum";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "shop";
	
	private static final String FILTER_FIND = "규칙";
		
	public CtShopClienService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
				
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValue("data-role", "list-title-text");
			
			for(Element el:es) {
				title = el.getElementsByTag("a").text();
				link = el.getElementsByTag("a").attr("href");
				
				if(title.length() > 0 && link.length() > 0) {
					if(title.contains(FILTER_FIND)) {
						continue;
					}
					
					link = HOST + link;
					
					addContent(title, link);					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
