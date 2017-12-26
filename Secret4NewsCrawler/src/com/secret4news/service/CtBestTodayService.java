package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtBestTodayService extends BaseService {

	private static final String NAME = "Today Humor";
	private static final String HOST = "http://m.todayhumor.co.kr/";
	private static final String URL = "http://m.todayhumor.co.kr/list.php?table=bestofbest";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "best";
	
	public CtBestTodayService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
		String reply = null;
		
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValueContaining("href", "view.php");
			
			for(Element el:es) {				
				title = el.getElementsByAttributeValue("class", "listSubject").text();
				reply = el.getElementsByAttributeValue("class", "memo_count").text();
				link = el.getElementsByTag("a").attr("href");
				
				if(title.length() > 0 && link.length() > 0) {
					title = title.replace(reply, "");
					link = HOST + link;
				
					addContent(title, link);					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
