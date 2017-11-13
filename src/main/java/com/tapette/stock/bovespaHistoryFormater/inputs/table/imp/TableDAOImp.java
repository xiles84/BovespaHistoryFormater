package com.tapette.stock.bovespaHistoryFormater.inputs.table.imp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionEmptyFile;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionInvalidFormat;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionOutOfRangeDate;
import com.tapette.stock.bovespaHistoryFormater.inputs.extracters.Extracters;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.TableDAO;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.imp.StockEntriesGrupped;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.type.TypeStockEntry;
import com.tapette.stock.bovespaHistoryFormater.stock.Stock;

public class TableDAOImp implements TableDAO {

	//TODO create an imp for the extracters and inver the invocation, ie the table wont be an object in the extracter but the opposite

	private static Logger logger = LoggerFactory.getLogger( TableDAOImp.class );

	private HashMap<Stock, StockEntriesGrupped> hashStockName = new HashMap<Stock, StockEntriesGrupped>();
	private List<Extracters> extracters = new ArrayList<Extracters>();
	private int loopTimeout = 120;

	@Override
	public boolean addStockEntry(StockEntry e) {
		if(logger.isDebugEnabled())
			logger.debug(String.format("addStock has been called [%s:%s]" , this, e));
		if(hashStockName.get(e.getStock()) == null)
			hashStockName.put(e.getStock() , new StockEntriesGrupped());
		hashStockName.get(e.getStock()).add(e);
		return true;
	}

	@Override
	public StockEntriesGrupped getStockEntriesGrupped(Stock str) {
		if(hashStockName.isEmpty())
			precessExtracters();
		if(logger.isDebugEnabled())
			logger.debug(String.format("getStock has been called [%s:%s:%s]" , this, str, hashStockName.get(str) != null ?  hashStockName.get(str) : null));
		return hashStockName.get(str);
	}

	@Override
	public List<StockEntry> getRelativeDateEntries(Stock stock, int date) throws ExceptionOutOfRangeDate {
		return getRelativeDateEntries(stock, date, null);
	}
	
	@Override
	public List<StockEntry> getRelativeDateEntries(Stock stock, int date, TypeStockEntry typeStockEntry) throws ExceptionOutOfRangeDate {
		if(hashStockName.isEmpty())
			precessExtracters();
		return (hashStockName != null && hashStockName.get(stock) != null ) ? hashStockName.get(stock).getRelativeDateStockEntry(date, typeStockEntry) : null;
	}

	@Override
	public int size() {
		return this.hashStockName.size();
	}

	@Override
	public boolean addExtracter(Extracters extracter) {
		this.hashStockName.clear();
		this.extracters.add(extracter);
		return true;
	}

	@Override
	public boolean addExtracter(List<Extracters> extracter) {
		for (int i = 0; i < extracter.size(); i++)
			addExtracter(extracter.get(i));
		return true;
	}
	
	private boolean precessExtracters() {
		if(logger.isDebugEnabled())
			logger.debug("precessExtracters will initiate");
		for (int i = 0; i < extracters.size(); i++) {
			try {
				extracters.get(i).execute(this);
			} catch (IOException | ExceptionEmptyFile | ExceptionInvalidFormat e) {
				e.printStackTrace();
			}
		}
		hasFinished();
		return true;
	}
	
	private boolean hasFinished() {
		int loopCount = 0;
		while(!allExtractersFinished() && loopCount < loopTimeout*2) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			loopCount++;
		}
		return true;
	}
	
	private boolean allExtractersFinished() {
		if(logger.isDebugEnabled())
			logger.debug("allExtractersFinished will initiate");
		for (int i = 0; i < extracters.size(); i++)
			if(!extracters.get(i).hasFinished())
				return false;
		if(logger.isDebugEnabled())
			logger.debug(String.format("allExtractersFinished was [%s]" , true));
		return true;
	}

}
