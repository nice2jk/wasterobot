package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtShopPpomService extends BaseService {

	private static final String NAME = "PPOMPU SHOP";
	private static final String HOST = "http://m.ppomppu.co.kr/new/";
	private static final String URL = "http://m.ppomppu.co.kr/new/bbs_list.php?id=ppomppu";
	
	private static final String CATEGORY = "shop";
	
	private static final String ENCORDING = "EUC-KR";
	
	public CtShopPpomService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
				
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValue("class", "none-border");
			
			for(Element el:es) {
				title = el.getElementsByAttributeValue("class", "title").text();
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
