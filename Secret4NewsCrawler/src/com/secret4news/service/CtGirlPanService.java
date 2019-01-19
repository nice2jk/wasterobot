package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtGirlPanService extends BaseService {

	private static final String NAME = "PAN";
	private static final String HOST = "https://m.pann.nate.com";
	private static final String URL = "https://m.pann.nate.com/talk/c20025";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "girl";
	
	public CtGirlPanService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
		String titleSub = null;
		
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValue("class", "nothumb");
			
			for(Element el:es) {
				title = el.getElementsByAttributeValue("class", "tit").text();
				titleSub = el.getElementsByAttributeValue("class", "count").text();
				link = el.getElementsByTag("a").attr("href");
				
				if(title.length() > 0 && link.length() > 0) {
					title = title.substring(0, title.indexOf(titleSub));
					if(!link.contains("http")) {
						link = HOST + link;
					}
					
					addContent(title, link);	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
