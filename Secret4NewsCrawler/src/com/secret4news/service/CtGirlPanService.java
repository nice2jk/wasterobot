package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtGirlPanService extends BaseService {

	private static final String NAME = "PAN";
	private static final String HOST = "http://m.pann.nate.com/";
	private static final String URL = "http://m.pann.nate.com/talk/c20025";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "girl";
	
	public CtGirlPanService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
		String erase = null;
		
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByTag("li");
			
			for(Element el:es) {
				title = el.getElementsByAttributeValue("class", "tit").text();
				erase = el.getElementsByAttributeValue("class", "count").text();
				title = title.substring(0, title.indexOf(erase));
				link = el.getElementsByTag("a").attr("href");
				
				if(title.length() > 0 && link.length() > 0) {
					link = HOST + link.substring(link.indexOf("talk"), link.indexOf("?"));
					
					addContent(title, link);	
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
