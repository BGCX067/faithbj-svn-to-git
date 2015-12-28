package com.faithbj.shop.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

	public static Date getAYearLater(String now) {
		String[] ss = now.split(" ");
		String[] ss1 = ss[1].split(":");	
		GregorianCalendar d = new GregorianCalendar();
		d.add(Calendar.YEAR,+1);
		d.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ss1[0]));
		d.set(Calendar.MINUTE, Integer.parseInt(ss1[1]));
		d.set(Calendar.SECOND, Integer.parseInt(ss1[2]));
		return d.getTime();
	}

	public static Date getDaysAgo(int days) {
		GregorianCalendar d = new GregorianCalendar();
		d.add(Calendar.DATE, -days);
		d.set(Calendar.HOUR_OF_DAY, 0);
		d.set(Calendar.MINUTE, 0);
		d.set(Calendar.SECOND, 0);
		d.set(Calendar.MILLISECOND, 0);
		return d.getTime();
	}
	public static Date getNextYear(Date now) {
		GregorianCalendar d = new GregorianCalendar();
		d.add(Calendar.YEAR,+1);
//		d.set(Calendar.HOUR_OF_DAY, Integer.parseInt(ss1[0]));
//		d.set(Calendar.MINUTE, Integer.parseInt(ss1[1]));
//		d.set(Calendar.SECOND, Integer.parseInt(ss1[2]));
		return d.getTime();
	}
	
	
	public static String formateDate2String(String format,Date date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		String dateString = simpleDateFormat.format(date);
		return dateString;
	}
//	public static void main(String[] args) {
//		FieldBlock fieldBlock = new FieldBlock();
//		fieldBlock.setId("4028815f34e4e30e0134e527ca500001");
//		fieldBlock.setCode("11");
//		fieldBlock.setRendBlockList(null);
//		JsonConfig jsonConfig = new JsonConfig();
//		jsonConfig.setJsonPropertyFilter(new PropertyFilter(){
//			  public boolean apply(Object source, String name, Object value) {
//			  if(name.equals("rendBlockList")) {
//			  return true;
//			  } else {
//			  return false;
//			  }
//			  }
//			   
//			  });
////		jsonConfig.setExcludes(new String[] { "rendBlockList" });
//		JSONArray jsonArray = JSONArray.fromObject(fieldBlock);
////		try {
////			HttpServletResponse response = ServletActionContext.getResponse();
////			response.setContentType("text/html" + ";charset=UTF-8");
////			response.setHeader("Pragma", "No-cache");
////			response.setHeader("Cache-Control", "no-cache");
////			response.setDateHeader("Expires", 0);
////			response.getWriter().write(jsonArray.toString());
////			response.getWriter().flush();
////		} catch (IOException e) {
////			e.printStackTrace();
////		}
//		System.out.println(jsonArray.toString());
//	}
	
	 public static Date getStringToDate(String time,String formatString){
		 
		  DateFormat format= new SimpleDateFormat(formatString);           
		        Date d = null;  
		        try {  
		            d = format.parse(time);  
		        } catch (Exception e) {  
		            // TODO Auto-generated catch block  
		            e.printStackTrace();  
		        }  
		        //System.out.println(d.toString());
		        return d;
		   
	 }
}
