package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtXartMlbLikeService extends BaseService {

	private static final String NAME = "Podong";
	private static final String HOST = "https://www.podong.kr";
	private static final String URL = "https://www.podong.kr/community/17k.dong";
	
	private static final String CATEGORY = "xart";
	
	public CtXartMlbLikeService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
		
		try {
			Document doc = Jsoup.parse(getContents());
			Elements es = doc.getElementsByAttributeValue("class", "desc cf");
			
			for(Element el:es) {
				title = el.getElementsByTag("a").text();
				link = el.getElementsByTag("a").attr("href");
				
				link = HOST + link;
				
				if(title.length() > 0 && link.length() > 0) {
					addContent(title, link);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
