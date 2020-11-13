package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtBestClienService extends BaseService {

	private static final String NAME = "Client Best";
	private static final String HOST = "https://www.clien.net";
	private static final String URL = "https://www.clien.net/service/group/clien_all?&od=T33";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "best";
	
	private static final String FILTER_FIND = "규칙";
		
	public CtBestClienService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
		
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValue("data-role", "list-title");
			
			for(Element el:es) {
				title = el.getElementsByAttributeValue("class", "subject_fixed").text();
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
