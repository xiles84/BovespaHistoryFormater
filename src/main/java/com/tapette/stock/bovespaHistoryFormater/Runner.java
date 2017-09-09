package com.tapette.stock.bovespaHistoryFormater;

import com.tapette.stock.bovespaHistoryFormater.file.Formater;
import com.tapette.stock.bovespaHistoryFormater.file.table.TableDAO;

public class Runner {

	public static void main(String[] args) {
		test1(args);
//		StockGrouper aa = new StockGrouper();
//		TableSimple cc = aa.get("aa");
//		System.out.println(((Object)cc).hashCode());
	}
	
	
	
	public static void test1(String[] args) {
		Formater form = new Formater();
		form.setFileStr("C:\\Av_C\\GIT\\New folder\\BovespaHistoryFormater\\src\\main\\resources\\COTAHIST_A2017.TXT");
		try {
			TableDAO aa = form.execute();
			System.out.println(aa.getStrockNameTable("IVVB11").getProximunTimesPrice("20190603"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
