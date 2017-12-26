package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtMovieCine21Service extends BaseService {

	private static final String NAME = "Cine21";
	private static final String HOST = "http://www.cine21.com/";
	private static final String URL = "http://www.cine21.com/news/list/?idx=1";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "movi";
		
	public CtMovieCine21Service(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
				
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByTag("li");
			
			for(Element el:es) {
				title = el.getElementsByAttributeValue("class", "tit").text();
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
