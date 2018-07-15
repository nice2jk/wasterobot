package com.secret4news.common;

public class Constants {

	public static final boolean isServer = false;
	
	public static final String[] CATEGORY = {
		"best", "xart", "news", "itct", "base", "socc", "movi", "miss", "shop", "stoc", "devp"	
	};
	
	public static final String DEFAULT_CATEGORY = "BEST";
	public static final String DEFAULT_CHARSET = "UTF-8";
	
	// 0 = no, 1 = normal, 2 = recommend
	public static final int DEFAULT_STATUS = 1;
	
	// 30min, 1h, 2h, 3h, 4h, 5h, 6h, 10h
	public static final int[] FETCH_INTERVAL = {
		1800000, 3600000, 7200000, 10800000, 14400000, 18000000, 21600000, -1, -1, -1, 36000000
	};
}
