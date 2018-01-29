package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtNewsClienService extends BaseService {

	private static final String NAME = "Clien News";
	private static final String HOST = "https://www.clien.net";
	private static final String URL = "https://www.clien.net/service/board/news";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "itct";
	
	private static final String FILTER_FIND = "이용규칙";
		
	public CtNewsClienService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
				
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValue("class", "list_item symph_row");
			
			for(Element el:es) {
				title = el.getElementsByAttributeValue("data-role", "list-title-text").text();
				link = el.getElementsByAttributeValue("class", "list_subject").attr("href");
				
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
