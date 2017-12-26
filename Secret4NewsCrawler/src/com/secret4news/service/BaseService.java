package com.secret4news.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.secret4news.common.Constants;
import com.secret4news.dao.ContentDAO;
import com.secret4news.model.ContentVO;

public abstract class BaseService extends Thread {

	abstract void process();
		
	private ContentDAO contentDAO;
	private List<ContentVO> contentList;

	private String cpName;
	private String apiUrl;
	private String category;
	private int fetchInterval;
	
public BaseService(String cpName, String apiUrl, String category, int fetchInterval) {
		
		if(Constants.isServer == true) {
			contentDAO = ContentDAO.getInstance();
		}
		
		contentList = new ArrayList<ContentVO>();
				
		this.cpName = cpName;
		this.apiUrl = apiUrl;
		this.category = category;
		this.fetchInterval = fetchInterval;		
	}
	
	protected String getContents() {
		return this.getContents(Constants.DEFAULT_CHARSET);
	}

	public String getCpName() {
		return cpName;
	}

	protected String getContents(String charType) {
		try {
			String str = null;
			StringBuffer sb = new StringBuffer();
			
			if(charType == null) {
				charType = "UTF-8";
			}
			
			URL url = new URL(apiUrl);
			URLConnection connection = url.openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_8; en-US) AppleWebKit/532.5 (KHTML, like Gecko) Chrome/4.0.249.0 Safari/532.5");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), charType));
		
			while((str = br.readLine())!=null){				
				sb.append(str).append("\n");
			}
			
			br.close();
			
			return sb.toString();
		} catch (Exception e) {
			
		}
		
		return null;
	}
	
	public void addContent(String title, String link) {
		contentList.add(new ContentVO(cpName, title, link, category));
	}
	
	public void setContents() {
		if(contentList.size() == 0) {
			return;
		}
		
		for(int i = 0; i < contentList.size(); i++) {
			if(Constants.isServer) {
				contentDAO.setContent(contentList.get(i));
			} else {
				System.out.println(contentList.get(i));
			}
		}
	}

	@Override
	public void run() {
		try {
			if(Constants.isServer) {
				while(true) {
					process();
					
					setContents();
					
					System.out.println("THREAD PROCESS : " + new Timestamp(System.currentTimeMillis()) + " | " + cpName + " | " + contentList.size());
					
					Thread.sleep(this.fetchInterval);
				}
			} else {
				process();
				
				setContents();
				
				System.out.println("LOCAL PROCESS : " + new Timestamp(System.currentTimeMillis()) + " | " + cpName + " | " + contentList.size());
				
			}
		} catch (Exception e) {
			
		}
	}
}
