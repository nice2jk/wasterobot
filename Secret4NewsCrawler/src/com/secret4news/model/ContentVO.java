package com.secret4news.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.secret4news.common.Constants;

public class ContentVO {

	private String cid;
	private String cpName;
	private String title;
	private String link;
	private String category;
	private int status;
	
	public ContentVO(String cpName, String title, String link, String category) {
		this.title = title;
		this.link = link;
		this.cid = makeContentId(cpName + title);
		this.cpName = cpName;
		this.category = category;
		this.status = Constants.DEFAULT_STATUS;
	}
	
	private String makeContentId(String str) {
		String MD5 = "";

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes());

			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < byteData.length; i++) {
				sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
			}

			MD5 = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			MD5 = null;
		}

		return MD5;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		/*return "ContentVO [id=" + id + ", cpName=" + cpName + ", title=" + title + ", link=" + link + ", category="
				+ category + ", status=" + status + "]";*/
		
		return cid + " | " + category + " | " + cpName + " | " + title + " | " + link;
	}
}
