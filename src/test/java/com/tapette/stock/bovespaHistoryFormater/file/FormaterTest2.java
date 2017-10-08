package com.tapette.stock.bovespaHistoryFormater.file;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp.ExtracterBovespaXLS;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ExtracterBovespaXLS.class)
public class FormaterTest2 {
	
	@Test
	public void fakeTest() throws Exception {
		
	}

	/*@Test
	public void testExecute() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		File file = new File(classLoader.getResource("stocks/").getFile());
		Formater form = new Formater(file.getAbsolutePath());
		StockEntry stock = new StockEntry("012017010212ABCP11      010FII ABC IMOBCI  ER       R$  000000000118300000000011830000000001101000000000116200000000011360000000001138000000000120500019000000000000001210000000000001407143000000000000009999123100000010000000000000BRABCPCTF000255");
		form.execute();
		for (int i = 0; i < form.getList().get(4).getFields().size(); i++)
			assertEquals(stock.getFields().get(i).toString() , form.getList().get(4).getFields().get(i).toString());
		assertEquals( 14, form.getList().size());
	}*/

	/*@Test
	public void testExecuteSpecificStock() throws Exception {
		System.out.println("aaa");
		Formater form = PowerMockito.spy(new Formater(Mockito.anyString()));
		PowerMockito.doNothing().when(form.execute());
		PowerMockito.doAnswer(new Answer<File>() {
			@Override
			public File answer(InvocationOnMock invocation) throws Throwable {
				return new File("") {
					private static final long serialVersionUID = 1L;
					@Override
					public File[] listFiles() {return new File[] {this};}
					@Override
					public boolean isFile() {return true;}
				};
			}
		}).when(form, PowerMockito.method(Formater.class , "getResource")).withArguments(Matchers.any());
		PowerMockito.doReturn(null).when(form , PowerMockito.method(Formater.class, "bufferedReader")).withArguments(Matchers.any());
		PowerMockito.doAnswer(new Answer<String>() {
			private int count = 0;
			public String answer(InvocationOnMock invocation) {
				count++;
				if(count == 1)
					return "00COTAHIST.2017BOVESPA 20170906                                                                                                                                                                                                                      ";
				else if(count == 2)
					return "012017010212ABCP11      010FII ABC IMOBCI  ER       R$  000000000118300000000011830000000001101000000000116200000000011360000000001138000000000120500019000000000000001210000000000001407143000000000000009999123100000010000000000000BRABCPCTF000255";
				else if( count == 3)
					return "99COTAHIST.2017BOVESPA 2017090600000330110                                                                                                                                                                                                           ";
				return null;
			}
		}).when(form, PowerMockito.method(Formater.class , "readLine")).withArguments(Matchers.any());
		PowerMockito.doReturn(null).when(form , PowerMockito.method(Formater.class, "fileReader")).withArguments(Matchers.any());
		StockEntryXLSImp stock = new StockEntryXLSImp("012017010212ABCP11      010FII ABC IMOBCI  ER       R$  000000000118300000000011830000000001101000000000116200000000011360000000001138000000000120500019000000000000001210000000000001407143000000000000009999123100000010000000000000BRABCPCTF000255");
		PowerMockito.whenNew(Formater.class).withAnyArguments().thenReturn(form);
		PowerMockito.doAnswer(new Answer<ArrayList<String>>() {

			@Override
			public ArrayList<String> answer(InvocationOnMock invocation) throws Throwable {
				ArrayList<String> aa = new ArrayList<String>();
				aa.add("");
				aa.add("");
				return aa;
			}
		}).when(form , PowerMockito.method(Formater.class , "getFileDir")).withNoArguments();
//		PowerMockito.doCallRealMethod().when(form , PowerMockito.method(Formater.class , "execute")).withNoArguments();
		PowerMockito.doCallRealMethod().when(form , PowerMockito.method(Formater.class , "run" , File.class));
		form.execute();
//		assertTrue(form.execute());
		for (int i = 0; i < form.getList().size(); i++)
			for (int j = 0; j < form.getList().get(i).getFields().size(); j++) {
				System.out.println(form.getList().get(i).getFields());
				System.out.println(stock.getFields().get(j).toString() + " === " + form.getList().get(i).getFields().get(j).toString());
				assertEquals(stock.getFields().get(j).toString() , form.getList().get(i).getFields().get(j).toString());
			}
	}*/


}
