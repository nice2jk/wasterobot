package com.secret4news.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CtDevPiaService extends BaseService {

	private static final String NAME = "DEVPIA";
	private static final String HOST = "http://m.devpia.com/";
	private static final String URL = "http://m.devpia.com/BoardList.aspx?BoardID=69&MaeulNo=28";
	
	private static final String ENCORDING = null;
	private static final String CATEGORY = "devp";
	
	private static final String FILTER = "title";
	
	public CtDevPiaService(int intervalTime) {
		super(NAME, URL, CATEGORY, intervalTime);
	}

	@Override
	void process() {
		String title = null;
		String link = null;
		
		try {
			Document doc = Jsoup.parse(getContents(ENCORDING));
			Elements es = doc.getElementsByAttributeValue("class", FILTER);
			
			for(Element el:es) {
				title = el.getElementsByTag("h3").text();
				link = el.getElementsByTag("a").attr("href");
				
				if(title.length() > 0 && link.length() > 0) {
					title = title.substring(0, title.indexOf('['));
					link = (HOST + link.substring(link.indexOf("Board"))).replace("page=1&", "");
					addContent(title, link);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
