package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtStockEconomicService extends BaseService {

	private static final String NAME = "Economic Post";
	private static final String HOST = "http://iluvmagic.tistory.com";
	private static final String URL = "http://iluvmagic.tistory.com/category/%EA%B2%BD%EC%A0%9C%26%ED%88%AC%EC%9E%90#";
	
	private static final String ENCORDING = null;
	
	private static final String CATEGORY = "stoc";
	
	public CtStockEconomicService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
				
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByTag("ol");
			es = es.get(0).getElementsByTag("li");
			
			for(Element el:es) {
				title = el.getElementsByTag("a").text();
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
