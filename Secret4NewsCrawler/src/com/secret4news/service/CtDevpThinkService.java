package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtDevpThinkService extends BaseService {

	private static final String NAME = "OKKY";
	private static final String HOST = "https://okky.kr/article";
	private static final String URL = "https://okky.kr/articles/community";
		
	private static final String ENCORDING = null;
	
	private static final String CATEGORY = "devp";
	
	public CtDevpThinkService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
				
		try {
			System.out.println(getContents(ENCORDING));
			Document doc = Jsoup.parse(getContents(ENCORDING));			
			Elements es = doc.getElementsByAttributeValue("class", "list-group-item-heading");

			for(Element el:es) {
				title = el.getElementsByTag("a").text();
				System.out.println(title);
				link = el.getElementsByTag("a").attr("href");
				System.out.println(link);				
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
