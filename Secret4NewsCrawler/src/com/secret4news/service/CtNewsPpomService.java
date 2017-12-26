package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtNewsPpomService extends BaseService {

	private static final String NAME = "PPOMPPU News";
	private static final String HOST = "http://m.ppomppu.co.kr/new/";
	private static final String URL = "http://m.ppomppu.co.kr/new/bbs_list.php?id=news2";
	
	private static final String ENCORDING = "EUC-KR";
	
	private static final String CATEGORY = "itct";
	
	private static final String FILTER_FIND = "필독";
		
	public CtNewsPpomService(int intervalTime) {
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
					link = HOST + link.substring(0, link.lastIndexOf('&'));
					
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
