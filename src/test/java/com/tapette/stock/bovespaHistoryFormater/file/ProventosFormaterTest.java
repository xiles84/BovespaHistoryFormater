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

import com.tapette.stock.bovespaHistoryFormater.file.ProventosFormater.SubThread;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ProventosFormater.class, SubThread.class})
public class ProventosFormaterTest {

	@Test
	public void test() throws Exception {
		ArrayList<Stock> stocks = new ArrayList<>();
		stocks.add(new Stock("MFII" , "MFII11"));
		ProventosFormater form = new ProventosFormater(stocks);
		form.execute();
		while(!form.hasFinished()) {
			Thread.sleep(2000);
		}
		fail("Not yet implemented");
	}
	
	@Test
	public void test2() throws Exception {
		ArrayList<Stock> stocks = new ArrayList<>();
		stocks.add(new Stock("MFII" , "MFII11"));
		ProventosFormater proventosFormater = new ProventosFormater(stocks);
		SubThread aa = PowerMockito.spy(proventosFormater.new SubThread(new URL("http://www.test.com")));
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
						SubThread.class,
						"getProventos")).
		withNoArguments();
		aa.run();
	}

}
