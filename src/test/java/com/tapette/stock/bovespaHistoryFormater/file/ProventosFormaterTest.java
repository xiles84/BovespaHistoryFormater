package com.tapette.stock.bovespaHistoryFormater.file;

import static org.junit.Assert.*;

import java.net.URL;
import java.util.ArrayList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp.ExtracterProventosBovespa;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.imp.WebMultiThread;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.parsers.imp.ParserBovespaProventos;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.imp.TableDAOImp;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ExtracterProventosBovespa.class, WebMultiThread.class})
public class ProventosFormaterTest {

	@Test
	public void test() throws Exception {
		ArrayList<Stock> stocks = new ArrayList<>();
		stocks.add(new Stock("MFII" , "MFII11", 2));
		ExtracterProventosBovespa form = new ExtracterProventosBovespa(stocks);
		form.execute();
		assertNotNull(form);
		assertNotNull(form.getList());
	}
	
	@Test
	public void test2() throws Exception {
		ArrayList<Stock> stocks = new ArrayList<>();
		stocks.add(new Stock("MFII" , "MFII11", 2));
		WebMultiThread aa = PowerMockito.spy(new WebMultiThread(stocks.get(0), new TableDAOImp(), new ParserBovespaProventos()));
		PowerMockito.doAnswer(new Answer<ArrayList<String>>() {
			@Override
			public ArrayList<String> answer(InvocationOnMock invocation) throws Throwable {
				String str = "		<td>RENDIMENTO</td><td align=\"left\">BRMFIICTF008</td><td align=\"center\">29/09/2017</td><td align=\"center\">29/09/2017</td><td align=\"right\">1,17000000000</td><td align=\"center\">Setembro/2017</td><td align=\"center\">16/10/2017</td><td>\r\n";
				ArrayList<String> bb = new ArrayList<String>();
				bb.add(str);
				return bb;
			}
		}).when(
				aa,
				PowerMockito.method(
						WebMultiThread.class,
						"getLines")).
		withNoArguments();
		aa.run();
	}

}
