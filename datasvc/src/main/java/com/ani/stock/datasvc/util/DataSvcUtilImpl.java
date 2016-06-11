package com.ani.stock.datasvc.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Component;

@Component("dataSvcUtil")
public class DataSvcUtilImpl implements DataSvcUtil {
	
	public String adjustCalendarDatesForYahoo(Calendar cal,
			DateFormat dateFormat) {
		String today = dateFormat.format(cal.getTime());
		if(cal.get((Calendar.DAY_OF_WEEK)) == Calendar.MONDAY) {
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) -6);
		}else {
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) -1);
		}
		return today;
	}
	
	public String dateToString(Calendar cal){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String stringedDate = dateFormat.format(cal.getTime());
		return stringedDate;
	}

}
