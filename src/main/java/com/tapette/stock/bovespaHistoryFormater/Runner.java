package com.tapette.stock.bovespaHistoryFormater;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tapette.stock.bovespaHistoryFormater.file.Formater;
import com.tapette.stock.bovespaHistoryFormater.file.table.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.file.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.file.table.stocks.imp.StockGroup;
import com.tapette.stock.bovespaHistoryFormater.math.StrockMath;
import com.tapette.stock.bovespaHistoryFormater.math.imp.MathImp;
import com.tapette.stock.bovespaHistoryFormater.math.imp.MathIncrementalImp;

public class Runner {

	public static void main(String[] args) throws Exception {
		//		realCode(args);
		realCode(args);
		//		StockGrouper aa = new StockGrouper();
		//		TableSimple cc = aa.get("aa");
		//		System.out.println(((Object)cc).hashCode());
	}



	public static void test1(String[] args) {
		//		form.setFileStr("C:\\Users\\Xiles84\\Downloads\\Java\\GIT\\BovespaHistoryFormater\\src\\main\\resources\\stocks\\COTAHIST_A2017.TXT");
		try {
			Formater form = new Formater("stocks/");
			HashMap<String, StockEntry> teste = new HashMap<>();
			teste.put("teste1" , null);
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
		//		form.setFileStr("C:\\Users\\Xiles84\\Downloads\\Java\\GIT\\BovespaHistoryFormater\\src\\main\\resources\\stocks\\COTAHIST_A2017.TXT");
		//		form.setFileStr("C:\\Users\\Xiles84\\Downloads\\Java\\GIT\\BovespaHistoryFormater\\src\\main\\resources\\stocks\\COTAHIST_A2017.TXT");
		try {
			Formater form = new Formater(path);
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

	public static void realCode(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("BovespaHistoryFormater.xml");

//		Logger log = Logger.getLogger(Runner.class);

		PropertyConfigurator.configureAndWatch("log4j.properties",15000);

		MathImp cc = (MathImp)context.getBean("Math");
		MathIncrementalImp dd = (MathIncrementalImp)context.getBean("MathIncremental");
		int[] ff = (int[])context.getBean("TupleImp");

		System.out.println("===========NUMDAYS===========");
		
//		System.out.println(((List<String>)context.getBean("StockGroupDates")).size());
		
		System.out.println("===========MATRIX============");
		try {
			for (int i = 0; i < dd.getPonderedCovariance(ff).length; i++) {
				for (int j = 0; j < dd.getPonderedCovariance(ff)[i].length-1; j++) {
					System.out.print(dd.getPonderedCovariance(ff)[i][j]+";");
				}
				System.out.println(dd.getPonderedCovariance(ff)[i][dd.getPonderedCovariance(ff)[i].length-1]);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("==========PRICE==============");


		for (int i = 0; i < cc.getMatrix()[cc.getMatrix().length-1].length-1; i++)
			System.out.print(cc.getMatrix()[cc.getMatrix().length-1][i]+";");
		System.out.println(cc.getMatrix()[cc.getMatrix().length-1][cc.getMatrix()[cc.getMatrix().length-1].length-1]);

		System.out.println("==========MEANS==============");


		for (int i = 0; i < dd.getPonderedMeans(ff).length-1; i++)
			System.out.print((dd.getPonderedMeans(ff)[i]+1)+";");
		System.out.println((dd.getPonderedMeans(ff)[dd.getPonderedMeans(ff).length-1]+1));
		
		
//		System.out.println(Arrays.toString(ff));

		//		StrockMath aa = (StrockMath)context.getBean("Math");

		/*for (int i = 0; i < aa.getSimpleCovariance().length; i++) {
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

		 */
	}

	public class teste{

		public String exec() throws IOException {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("stocks/").getFile());
			return file.getCanonicalPath().toString();
		}

	}

}
