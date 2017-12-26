package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtBaseMlbparkService extends BaseService {

	private static final String NAME = "MLBPark Baseball";
	private static final String URL = "http://mlbpark.donga.com/mbs/articleL.php?mbsC=kbotown2";
	
	private static final String CATEGORY = "base";
		
	public CtBaseMlbparkService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;
		String link = null;
		
		try {
			Document doc = Jsoup.parse(getContents());
			Elements es = doc.getElementsByAttributeValue("class", "title");
						
			for(Element el:es) {
				title = el.getElementsByTag("div").text();
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
