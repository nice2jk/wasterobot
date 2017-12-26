package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtGirlMizService extends BaseService {

	private static final String NAME = "MizNet";
	private static final String URL = "http://m.miznet.daum.net/";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "girl";
	
	public CtGirlMizService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;
		String link = null;
		
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValue("class", "link_talk");
			
			for(Element el:es) {
				title = el.getElementsByTag("p").text();
				link = el.getElementsByTag("a").attr("href");
				
				if(title.length() > 0 && link.length() > 0) {
					link = link.substring(0, link.indexOf("&page"));
					
					addContent(title, link);	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
