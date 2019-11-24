package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtGirl82cookService extends BaseService {

	private static final String NAME = "82Cook";
<<<<<<< HEAD
	private static final String HOST = "http://www.82cook.com/entiz/";
=======
	private static final String HOST = "http://www.82cook.com";
>>>>>>> refs/remotes/origin/master
	private static final String URL = "https://www.82cook.com/entiz/enti.php?bn=15";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "girl";
	
	public CtGirl82cookService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;
		String link = null;
		
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
<<<<<<< HEAD
			Elements es = doc.getElementsByAttributeValue("class", "title");
=======
			Elements es = doc.getElementsByAttributeValueStarting("href", "/entiz/read.");
>>>>>>> refs/remotes/origin/master
			
			for(Element el:es) {
<<<<<<< HEAD
				title = el.getElementsByTag("a").text();
				reply = el.getElementsByTag("em").text();
				title = title.replace(reply, "");
=======
				title = el.attr("title");
				link = el.attr("href");
>>>>>>> refs/remotes/origin/master
				
<<<<<<< HEAD
				link = el.getElementsByTag("a").attr("href");
				
				if(title.length() > 0 && link.length() > 0 && link.contains("&")) {
					link = HOST + link;
					
					if(title.length() > 0 && link.length() > 0) {
						addContent(title, link);	
					}
=======
				if(title.length() > 0 && link.length() > 0) {
					link = HOST + link;
					addContent(title, link);
>>>>>>> refs/remotes/origin/master
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
