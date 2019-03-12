package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtStockPoxnetService extends BaseService {

	private static final String NAME = "PoxNet";
	private static final String URL = "http://www.paxnet.co.kr/tbbs/list?tbbsType=L&id=N10841";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "stoc";
	
	public CtStockPoxnetService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;		
		String link = null;
				
		try {
			System.out.println(getContents(ENCORDING));
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValue("class", "best-title");
			
			for(Element el:es) {
				title = el.getElementsByTag("a").text();
				System.out.println(title);
				link = el.getElementsByTag("a").attr("href");
				System.out.println(link);
				if(title.length() > 0 && link.length() > 0) {
					
					
					addContent(title, link);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
