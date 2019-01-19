package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtNewsItcleService extends BaseService {

	private static final String NAME = "techneedle";
	private static final String URL = "http://techneedle.com";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "itct";
		
	public CtNewsItcleService(int intervalTime) {
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
				title = el.getElementsByTag("h1").text();
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
