package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtNewsItcleService extends BaseService {

	private static final String NAME = "ByLine";
	private static final String URL = "https://byline.network/amp/";
	
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
			Elements es = doc.getElementsByAttributeValue("class", "fsp-img");
			
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
