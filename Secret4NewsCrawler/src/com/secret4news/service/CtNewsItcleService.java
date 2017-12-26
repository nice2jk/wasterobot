package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtNewsItcleService extends BaseService {

	private static final String NAME = "ITCLE";
	private static final String URL = "http://www.itcle.com/category/itcle/";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "itct";
		
	public CtNewsItcleService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
				
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByTag("article");
			
			for(Element el:es) {
				title = el.getElementsByTag("a").get(0).attr("title");
				link = el.getElementsByTag("a").get(0).attr("href");
				
				if(title.length() > 0 && link.length() > 0) {
					addContent(title, link);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
