package com.tapette.stock.bovespaHistoryFormater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.file.Formater;
import com.tapette.stock.bovespaHistoryFormater.file.table.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.file.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.file.table.stocks.StockGroup;

public class Runner {

	public static void main(String[] args) {
		test2(args);
//		StockGrouper aa = new StockGrouper();
//		TableSimple cc = aa.get("aa");
//		System.out.println(((Object)cc).hashCode());
	}
	
	
	
	public static void test1(String[] args) {
		Formater form = new Formater();
		form.setFileStr("C:\\Users\\Xiles84\\Downloads\\Java\\GIT\\BovespaHistoryFormater\\src\\main\\resources\\COTAHIST_A2017.TXT");
		try {
			HashMap<String, StockEntry> teste = new HashMap<>();
			teste.put("teste1" , null);
			
			form.execute();
			TableDAO aa = form.getList();
			System.out.println(aa.getStrockNameTable("IVVB11").
					getProximunTimesPrice("20190603"));
			System.out.println("");
			
			List<String> dates = new ArrayList<String>();
			dates.add("20170603");
			dates.add("20170604");
			dates.add("20170605");
			dates.add("20170606");
			dates.add("20170607");
			dates.add("20170608");
			dates.add("20170609");
			dates.add("20170610");
			dates.add("20170620");
			
			List<String> namesL = new ArrayList<String>();
			namesL.add("IVVB11");
			namesL.add("ITUB11");
			namesL.add("BOVA11");
			namesL.add("MOBI11");
			
			StockGroup nn = new StockGroup(namesL, aa, StockGroup.rotateDate("20170102", "20171003", 7, 100));
			
			nn.execute();
			for (int i = 0; i < nn.getResultStringArray().length; i++) {
				for (int k = 0; k < nn.getResultStringArray()[0].length; k++) {
					System.out.print(nn.getResultStringArray()[i][k] + " ");
				}
				System.out.println("");
			}
			
			for (int i = 0; i < nn.getResultIntArray().length; i++) {
				for (int k = 0; k < nn.getResultIntArray()[0].length; k++) {
					System.out.print(nn.getResultIntArray()[i][k] + " ");
				}
				System.out.println("");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void test2(String[] args) {
		Formater form = new Formater();
		form.setFileStr("C:\\Users\\Xiles84\\Downloads\\Java\\GIT\\BovespaHistoryFormater\\src\\main\\resources\\COTAHIST_A2017.TXT");
		try {
			HashMap<String, StockEntry> teste = new HashMap<>();
			teste.put("teste1" , null);
			
			form.execute();
			TableDAO aa = form.getList();
			System.out.println(aa.getStrockNameTable("IVVB11").
					getProximunTimesPrice("20190603"));
			System.out.println("");
			
			List<String> namesL = new ArrayList<String>();
			namesL.add("MFII11");
			namesL.add("PLRI11");
			namesL.add("KNCR11");
			namesL.add("BCRI11");
			namesL.add("CPTS11B");
			namesL.add("XPCM11");
			
			StockGroup nn = new StockGroup(namesL, aa, StockGroup.rotateDate("20170102", "20171003", 7, 100));
			
			nn.execute();
			
			Math math = new Math(nn.getResultIntArray());
			math.getMeans();
			math.getCovariance();
			
			for (int i = 0; i < math.getMeans().length; i++)
				System.out.print(math.getMeans()[i]+ "\t\t\t");
			
			System.out.println("\n\n\n\n");
			
			for (int i = 0; i < nn.getResultIntArray().length; i++) {
				for (int j = 0; j < nn.getResultIntArray()[i].length; j++) {
					System.out.print(nn.getResultIntArray()[i][j] + "\t\t\t");
				}
				System.out.print("\n");
			}
			
			System.out.println("\n\n\n\n");
			
			for (int i = 0; i < math.getIncrements().length; i++) {
				for (int j = 0; j < math.getIncrements()[i].length; j++) {
					System.out.print(math.getIncrements()[i][j] + "\t\t\t");
				}
				System.out.print("\n");
			}
			
			System.out.println("\n\n\n\n");
			
			for (int i = 0; i < math.getCovariance().length; i++) {
				for (int j = 0; j < math.getCovariance()[i].length; j++) {
					System.out.print(math.getCovariance()[i][j] + "\t\t\t");
				}
				System.out.print("\n");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
