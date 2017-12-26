package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtStockPoxnetService extends BaseService {

	private static final String NAME = "PoxNet";
	private static final String URL = "http://bbs.moneta.co.kr/nbbs/bbs.mpax.lst.screen?p_bbs_id=N10584";
	
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
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValueContaining("onclick", "message_view");
			
			for(Element el:es) {
				title = el.getElementsByTag("dt").text();
				link = el.getElementsByTag("dl").attr("onclick");
				
				if(title.length() > 0 && link.length() > 0) {
					link = (URL.replace("lst", "qry")).replace("?", "?p_message_id=" + link.substring(link.indexOf('\'') + 1, link.indexOf(',') - 1) + "&");
					
					addContent(title, link);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
