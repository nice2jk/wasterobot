package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtXartMlbParkService extends BaseService {

	private static final String NAME = "MLBPark Dangerous";
	private static final String URL = "http://mlbpark.donga.com/mp/b.php?select=sct&m=search&b=bullpen&select=sct&query=%EC%A3%BC%EB%B2%88%EB%82%98&x=0&y=0";
	
	private static final String CATEGORY = "xart";
	
	public CtXartMlbParkService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
		
		try {
			Document doc = Jsoup.parse(getContents());
			Elements es = doc.getElementsByAttributeValue("class", "t_left");
			
			for(Element el:es) {
				title = el.getElementsByTag("a").attr("title");
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
