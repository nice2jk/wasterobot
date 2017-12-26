package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtMovieNaverService extends BaseService {

	private static final String NAME = "Naver Magazine";
	private static final String HOST = "http://m.movie.naver.com";	
	private static final String URL = "http://m.movie.naver.com/m/magazine/list.nhn?menu=SPECIAL_REPORT";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "movi";
		
	public CtMovieNaverService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
				
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValueContaining("href", "detail.nhn");
			
			
			for(Element el:es) {				
				title = el.getElementsByAttributeValue("class", "sprt_hlt").text();
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
