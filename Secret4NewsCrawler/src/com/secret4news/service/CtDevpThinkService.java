package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtDevpThinkService extends BaseService {

	private static final String NAME = "Byline";
	private static final String URL = "https://byline.network/amp/";
		
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
			
			Document doc = Jsoup.parse(getContents(ENCORDING));			
			Elements es = doc.getElementsByTag("h2");

			for(Element el:es) {
				title = el.getElementsByTag("a").text();
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
