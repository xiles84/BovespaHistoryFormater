package com.tapette.stock.bovespaHistoryFormater;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tapette.stock.bovespaHistoryFormater.file.Formater;
import com.tapette.stock.bovespaHistoryFormater.file.table.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.file.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.file.table.imp.TableDAOImp;
import com.tapette.stock.bovespaHistoryFormater.file.table.stocks.imp.StockGroup;
import com.tapette.stock.bovespaHistoryFormater.math.StrockMath;
import com.tapette.stock.bovespaHistoryFormater.math.imp.MathImp;
import com.tapette.stock.bovespaHistoryFormater.math.imp.MathIncrementalImp;

public class Runner {

	public static void main(String[] args) throws Exception {
		//		realCode(args);
		test2(args);
		//		StockGrouper aa = new StockGrouper();
		//		TableSimple cc = aa.get("aa");
		//		System.out.println(((Object)cc).hashCode());
	}



	public static void test1(String[] args) {
		String path = "C:\\Users\\Xiles84\\Downloads\\Java\\GIT\\BovespaHistoryFormater\\src\\main\\resources\\stocks\\COTAHIST_A2017.TXT";
		Formater form = new Formater(path);
//		form.setFileStr("C:\\Users\\Xiles84\\Downloads\\Java\\GIT\\BovespaHistoryFormater\\src\\main\\resources\\stocks\\COTAHIST_A2017.TXT");
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
		String path = "C:\\Users\\Xiles84\\Downloads\\Java\\GIT\\BovespaHistoryFormater\\src\\main\\resources\\stocks\\COTAHIST_A2017.TXT";
		Formater form = new Formater(path);
		//		form.setFileStr("C:\\Users\\Xiles84\\Downloads\\Java\\GIT\\BovespaHistoryFormater\\src\\main\\resources\\stocks\\COTAHIST_A2017.TXT");
		//		form.setFileStr("C:\\Users\\Xiles84\\Downloads\\Java\\GIT\\BovespaHistoryFormater\\src\\main\\resources\\stocks\\COTAHIST_A2017.TXT");
		try {
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

			StrockMath math = new MathIncrementalImp(nn.getResultIntArray());
			math.getMeans();
			math.getSimpleCovariance();

			System.out.println(Arrays.toString(math.getMeans()));

			System.out.println("");

			for (int i = 0; i < nn.getResultIntArray().length; i++) {
				System.out.println(Arrays.toString(nn.getResultIntArray()[i]));
			}

			System.out.println("");

			for (int i = 0; i < math.getMatrix().length; i++) {
				System.out.println(Arrays.toString(math.getMatrix()[i]));
			}

			System.out.println("");

			for (int i = 0; i < math.getSimpleCovariance().length; i++) {
				System.out.println(Arrays.toString(math.getSimpleCovariance()[i]));
				System.out.print("");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void test3(String[] args) {


		File[] files = new File("./").listFiles();

		for (File file : files) {
			if (file.isFile()) {
				System.out.println((file.getName()));
			}
		}
		System.out.println(System.getProperties());

	}

	public static void realCode(String[] args) throws Exception {

		ApplicationContext context = new ClassPathXmlApplicationContext("BovespaHistoryFormater.xml");

		Logger log = Logger.getLogger(Runner.class);

		PropertyConfigurator.configureAndWatch("log4j.properties",15000);

		StrockMath aa = (StrockMath)context.getBean("Math");

		for (int i = 0; i < aa.getSimpleCovariance().length; i++) {
			for (int j = 0; j < aa.getSimpleCovariance()[0].length; j++) {
				System.out.print(aa.getSimpleCovariance()[i][j] + " ");
			}
			System.out.println();
		}


		System.out.println();
		System.out.println();
		System.out.println();

		for (int i = 0; i < aa.getMeans().length; i++) {
			System.out.print(aa.getMeans()[i] + " ");
			System.out.println();
		}


	}

}
