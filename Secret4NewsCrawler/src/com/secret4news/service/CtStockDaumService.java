package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtStockDaumService extends BaseService {

	private static final String NAME = "Daum Stock";	
	private static final String URL = "http://m.finance.daum.net/m/community/index.daum";
	
	private static final String ENCORDING = null;
	
	private static final String CATEGORY = "stoc";
	
	public CtStockDaumService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
				
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValue("class", "link_debate");
			
			for(Element el:es) {
				title = el.getElementsByAttributeValue("class", "tit_subject").text();
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
