package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtBestDdanziService extends BaseService {

	private static final String NAME = "The ddanzi";
	private static final String URL = "http://www.ddanzi.com/index.php?mid=free&statusList=HOT%2CHOTBEST";
	
	private static final String ENCORDING = null;	
	private static final String CATEGORY = "best";
		
	public CtBestDdanziService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;
		String link = null;
		
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValueContaining("href", "document_srl");
			/*Elements es = doc.getElementsByAttributeValue("class", "title");*/
			
			for(Element el:es) {
				title = el.getElementsByTag("a").text();
				link = el.getElementsByTag("a").attr("href");
								
				if(title.length() > 0 && link.length() > 0) {
					if(link.contains("comment") || link.contains("class")) {
						continue;
					}
					
					addContent(title, link);					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
