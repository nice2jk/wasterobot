package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtMovieExtremeService extends BaseService {

	private static final String NAME = "Ext Movie";
	private static final String URL = "http://www.extmovie.com/xe/news";
	
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
			Elements es = doc.getElementsByAttributeValue("class", "has_thumb");
						
			for(Element el:es) {
				title = el.getElementsByTag("a").attr("title");
				link = el.getElementsByTag("a").attr("href");
					
				if(title.length() > 0 && link.length() > 0) {
					addContent(title, link);					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
