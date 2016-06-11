package com.ani.stock.datasvc.yahoo.intraday;

import java.util.ArrayList;
import java.util.List;


//http://meumobi.github.io/stocks%20apis/2016/03/13/get-realtime-stock-quotes-yahoo-finance-api.html
public class YahooIntraday {
	
//	{  
//		  "list":{  
//		    "meta":{  
//		      "type":"resource-list",
//		      "start":0,
//		      "count":1
//		    },
//		    "resources":[  
//		      {  
//		        "resource":{  
//		          "classname":"Quote",
//		          "fields":{  
//		            "change":"1.320000",
//		            "chg_percent":"3.937947",
//		            "day_high":"34.990002",
//		            "day_low":"34.000000",
//		            "issuer_name":"Cielo S.A.",
//		            "issuer_name_lang":"Cielo S.A.",
//		            "name":"CIELO       ON      NM",
//		            "price":"34.840000",
//		            "symbol":"CIEL3.SA",
//		            "ts":"1458236195",
//		            "type":"equity",
//		            "utctime":"2016-03-17T17:36:35+0000",
//		            "volume":"5405800",
//		            "year_high":"46.270000",
//		            "year_low":"28.030000"
//		          }
//		        }
//		      }
//		    ]
//		  }
//		}
//	
	
	private YahooEntity list = new YahooEntity();

	public YahooEntity getList() {
		return list;
	}

	public void setList(YahooEntity list) {
		this.list = list;
	}
	
	
	

}
