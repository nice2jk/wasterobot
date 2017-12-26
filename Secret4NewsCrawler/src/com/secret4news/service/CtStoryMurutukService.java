package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtStoryMurutukService extends BaseService {

	private static final String NAME = "물뚝심송";
	private static final String URL = "http://murutukus.kr";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "NEWS";
	
	public CtStoryMurutukService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
				
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValue("class", "entry-header");
			
			for(Element el:es) {
				title = el.getElementsByTag("a").text();
				link = el.getElementsByTag("a").attr("href");
				
				if(title.length() > 0 && link.length() > 0) {
										
					if(title.length() > 0 && link.length() > 0) {
						addContent(title, link);	
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
