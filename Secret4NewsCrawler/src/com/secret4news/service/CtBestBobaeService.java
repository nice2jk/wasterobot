package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtBestBobaeService extends BaseService {

	private static final String NAME = "BobaeDream";
	private static final String HOST = "https://m.bobaedream.co.kr";
	private static final String URL = "https://m.bobaedream.co.kr/board/new_writing/best";
	
	private static final String ENCORDING = null;
	
	private static final String CATEGORY = "best";
		
	public CtBestBobaeService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
		
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValue("class", "info");
			
			for(Element el:es) {				
				title = el.getElementsByAttributeValue("class", "cont").text();
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
