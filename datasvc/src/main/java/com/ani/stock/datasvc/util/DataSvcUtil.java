package com.ani.stock.datasvc.util;

import java.text.DateFormat;
import java.util.Calendar;

public interface DataSvcUtil {
	
	public String adjustCalendarDatesForYahoo(Calendar cal,
			DateFormat dateFormat);
	
	public String dateToString(Calendar cal);

}
