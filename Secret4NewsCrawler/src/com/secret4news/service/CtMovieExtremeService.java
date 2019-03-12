package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtMovieExtremeService extends BaseService {

	private static final String NAME = "Ext Movie";
	private static final String HOST = "http://extmovie.maxmovie.com";
	private static final String URL = "http://extmovie.maxmovie.com/xe/movietalk";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "movi";
		
	public CtMovieExtremeService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
				
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValue("class", "notice");
						
			for(Element el:es) {
				title = el.getElementsByTag("a").first().text();
				link = el.getElementsByTag("a").first().attr("href");
					
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
