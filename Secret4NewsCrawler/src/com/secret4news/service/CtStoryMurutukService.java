package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtStoryMurutukService extends BaseService {

	private static final String NAME = "dcnewsJ";
	private static final String HOST = "https://dcnewsj.joins.com";	
	private static final String URL = "https://dcnewsj.joins.com/all?cloc=dcnewsj|home|navi";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "news";
	
	public CtStoryMurutukService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
				
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValue("class", "mg text_wrap");
			
			for(Element el:es) {
				title = el.getElementsByTag("a").text();
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
