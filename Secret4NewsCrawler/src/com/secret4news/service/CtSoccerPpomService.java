package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtSoccerPpomService extends BaseService {

	private static final String NAME = "PPOMPPU Soccer";
	private static final String HOST = "http://m.ppomppu.co.kr/new/";
	private static final String URL = "http://m.ppomppu.co.kr/new/bbs_list.php?id=soccer";
	
	private static final String ENCORDING = "EUC-KR";
	
	private static final String CATEGORY = "socc";
	
	private static final String FILTER_FIND = "필독";
	
	public CtSoccerPpomService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
				
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValue("class", "noeffect");
			
			for(Element el:es) {
				title = el.getElementsByTag("strong").text();
				link = el.getElementsByTag("a").attr("href");
				
				if(title.length() > 0 && link.length() > 0) {					
					link = HOST + link;
					
					if(!title.contains(FILTER_FIND)) {
						addContent(title, link);	
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
