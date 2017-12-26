package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtXartClienService extends BaseService {

	private static final String NAME = "Clien XART";
	private static final String URL = "https://www.clien.net/service/search?q=%ED%9B%84%EB%B0%A9";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "xart";
		
	public CtXartClienService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
				
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValue("class", "list-subject");
			
			for(Element el:es) {
				title = el.text();
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
