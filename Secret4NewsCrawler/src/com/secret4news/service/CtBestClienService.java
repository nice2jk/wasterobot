package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtBestClienService extends BaseService {

	private static final String NAME = "Client Best";
	private static final String HOST = "https://www.clien.net";
	private static final String URL = "https://www.clien.net/service/group/clien_all?&od=T33";
	
	private static final String CATEGORY = "best";
	
	private static final String FILTER_FIND = "이용규칙";
		
	public CtBestClienService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
		
		try {
			System.out.println(getContents());
			Document doc = Jsoup.parse(getContents());
			Elements es = doc.getElementsByAttributeValue("class", "list-subject");
			
			for(Element el:es) {
				title = el.text();
				
				link = el.getElementsByTag("a").attr("href");
				
				if(title.length() > 0 && link.length() > 0) {
					if(title.contains(FILTER_FIND)) {
						continue;
					}
					
					link = HOST + link;
					
					addContent(title, link);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
