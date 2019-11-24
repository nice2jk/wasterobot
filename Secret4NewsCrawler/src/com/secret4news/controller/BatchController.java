package com.secret4news.controller;

import java.util.HashMap;
import java.util.Map;

import com.secret4news.common.Constants;
import com.secret4news.service.BaseService;
import com.secret4news.service.CtBaseMlbparkService;
import com.secret4news.service.CtBasePpomService;
import com.secret4news.service.CtBestBobaeService;
import com.secret4news.service.CtBestDdanziService;
import com.secret4news.service.CtBestClienService;
import com.secret4news.service.CtBestTodayService;
import com.secret4news.service.CtDevPiaService;
import com.secret4news.service.CtGirl82cookService;
import com.secret4news.service.CtGirlMizService;
import com.secret4news.service.CtGirlPanService;
import com.secret4news.service.CtMovieCine21Service;
import com.secret4news.service.CtMovieExtremeService;
import com.secret4news.service.CtMovieNaverService;
import com.secret4news.service.CtNewsClienService;
import com.secret4news.service.CtNewsItcleService;
import com.secret4news.service.CtNewsPpomService;
import com.secret4news.service.CtShopClienService;
import com.secret4news.service.CtShopPpomService;
import com.secret4news.service.CtSoccerGoalService;
import com.secret4news.service.CtSoccerPpomService;
import com.secret4news.service.CtStockDaumService;
import com.secret4news.service.CtStockEconomicService;
import com.secret4news.service.CtStockPoxnetService;
import com.secret4news.service.CtStoryMurutukService;
import com.secret4news.service.CtStoryPPSSService;
import com.secret4news.service.CtStorySlowNewsService;
import com.secret4news.service.CtDevpThinkService;
import com.secret4news.service.CtXartBBGirlService;
import com.secret4news.service.CtXartClienService;
import com.secret4news.service.CtXartMlbLikeService;
import com.secret4news.service.CtXartMlbParkService;

public class BatchController {
	
	private Map<String, BaseService> serviceMap;
	
	public BatchController() {
		serviceMap = new HashMap<String, BaseService>();
		
		// Best 4
		serviceMap.put("bestbb", new CtBestBobaeService(Constants.FETCH_INTERVAL[2]));
		serviceMap.put("bestdd", new CtBestDdanziService(Constants.FETCH_INTERVAL[1]));
		serviceMap.put("bestcl", new CtBestClienService(Constants.FETCH_INTERVAL[2]));
		serviceMap.put("besttb", new CtBestTodayService(Constants.FETCH_INTERVAL[1]));
		
		// xart 4
		serviceMap.put("xartcl", new CtXartClienService(Constants.FETCH_INTERVAL[2]));
		serviceMap.put("xartbb", new CtXartBBGirlService(Constants.FETCH_INTERVAL[2]));
		serviceMap.put("xartml", new CtXartMlbLikeService(Constants.FETCH_INTERVAL[1]));
		serviceMap.put("xartmp", new CtXartMlbParkService(Constants.FETCH_INTERVAL[1]));
		
		// news 3
		serviceMap.put("itctcl", new CtNewsClienService(Constants.FETCH_INTERVAL[2]));
		serviceMap.put("itctit", new CtNewsItcleService(Constants.FETCH_INTERVAL[3]));
		serviceMap.put("itctpp", new CtNewsPpomService(Constants.FETCH_INTERVAL[3]));
		
		// story 4
		serviceMap.put("newsdc", new CtStoryMurutukService(Constants.FETCH_INTERVAL[10]));
		serviceMap.put("newssl", new CtStorySlowNewsService(Constants.FETCH_INTERVAL[6]));
		serviceMap.put("newspp", new CtStoryPPSSService(Constants.FETCH_INTERVAL[5]));
				
		// base 2
		serviceMap.put("baseml", new CtBaseMlbparkService(Constants.FETCH_INTERVAL[3]));
		serviceMap.put("basepp", new CtBasePpomService(Constants.FETCH_INTERVAL[3]));
		
		// soccer 2
		serviceMap.put("soccgl", new CtSoccerGoalService(Constants.FETCH_INTERVAL[3]));
		serviceMap.put("soccpp", new CtSoccerPpomService(Constants.FETCH_INTERVAL[3]));
		
		// movie 3
		serviceMap.put("movi21", new CtMovieCine21Service(Constants.FETCH_INTERVAL[3]));
		serviceMap.put("moviex", new CtMovieExtremeService(Constants.FETCH_INTERVAL[2]));
		serviceMap.put("movinv", new CtMovieNaverService(Constants.FETCH_INTERVAL[4]));
		
		// girl 3
		serviceMap.put("girl82", new CtGirl82cookService(Constants.FETCH_INTERVAL[2]));
		/*serviceMap.put("girlmz", new CtGirlMizService(Constants.FETCH_INTERVAL[4]));*/
		serviceMap.put("girlpn", new CtGirlPanService(Constants.FETCH_INTERVAL[4]));
		
		// shop 2
		serviceMap.put("shopcl", new CtShopClienService(Constants.FETCH_INTERVAL[2]));
		serviceMap.put("shoppp", new CtShopPpomService(Constants.FETCH_INTERVAL[3]));
		
		// stock 3
		serviceMap.put("stocda", new CtStockDaumService(Constants.FETCH_INTERVAL[3]));
		/*serviceMap.put("stocec", new CtStockEconomicService(Constants.FETCH_INTERVAL[6]));*/
		serviceMap.put("stocpx", new CtStockPoxnetService(Constants.FETCH_INTERVAL[2]));
		
		// dev 2
		serviceMap.put("devpia", new CtDevPiaService(Constants.FETCH_INTERVAL[6]));
		serviceMap.put("devpok", new CtDevpThinkService(Constants.FETCH_INTERVAL[3]));
	}
	
	public void run(String param) {
		if(param.equals("all")) {
			System.out.println("Total Loop : " + serviceMap.size());
			
			int count = 0;
			
			for(String entry:serviceMap.keySet()) {
				serviceMap.get(entry).start();
				System.out.println(++count + " : Loop start : " + serviceMap.get(entry).getCpName());
			}
		} else {
			((BaseService)serviceMap.get(param)).start();
		}
	}
}
