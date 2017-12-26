package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtStorySlowNewsService extends BaseService {

	private static final String NAME = "Slow News";
	private static final String URL = "http://slownews.kr/archives";
		
	private static final String ENCORDING = null;
	
	private static final String CATEGORY = "news";
	
	public CtStorySlowNewsService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
				
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValue("class", "article-detail");
			
			for(Element el:es) {
				title = el.getElementsByAttributeValue("class", "article_title").text();
				link = el.getElementsByTag("a").attr("href");
				
				if(title.length() > 0 && link.length() > 0) {
					addContent(title, link);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
