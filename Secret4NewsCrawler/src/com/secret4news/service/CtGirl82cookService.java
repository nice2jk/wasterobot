package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtGirl82cookService extends BaseService {

	private static final String NAME = "82Cook";
	private static final String HOST = "http://www.82cook.com/entiz/";
	private static final String URL = "http://www.82cook.com/entiz/enti.php?bn=15";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "girl";
	
	public CtGirl82cookService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;
		String link = null;
		String reply = null;
		
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByTag("tr");
			
			for(Element el:es) {
				title = el.getElementsByAttributeValue("class", "title").text();
				reply = el.getElementsByTag("em").text();
				title = title.replace(reply, "");
				
				link = el.getElementsByTag("a").attr("href");
				
				if(title.length() > 0 && link.length() > 0 && link.contains("&")) {
					link = HOST + link.substring(0, link.lastIndexOf("&"));
					
					if(!reply.equals("") && Integer.valueOf(reply) > 5) {
						addContent(title, link);	
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
