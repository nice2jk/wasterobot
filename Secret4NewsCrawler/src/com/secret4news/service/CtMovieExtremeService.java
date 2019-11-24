package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtMovieExtremeService extends BaseService {

	private static final String NAME = "Ext Movie";
<<<<<<< HEAD
	private static final String HOST = "http://extmovie.maxmovie.com";
	private static final String URL = "https://extmovie.com/bestboard";
=======
	private static final String URL = "http://extmovie.maxmovie.com/xe/movietalk";
	private static final String HOST = "http://extmovie.maxmovie.com";
>>>>>>> refs/remotes/origin/master
	
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
			Elements es = doc.getElementsByAttributeValue("class", "title_area");
						
			for(Element el:es) {
				title = el.getElementsByTag("a").first().text();
				link = el.getElementsByTag("a").first().attr("href");
					
				if(title.length() > 0 && link.length() > 0) {
					link = HOST + link;
					
<<<<<<< HEAD
					addContent(title, link);
=======
					addContent(title, link);					
>>>>>>> refs/remotes/origin/master
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
