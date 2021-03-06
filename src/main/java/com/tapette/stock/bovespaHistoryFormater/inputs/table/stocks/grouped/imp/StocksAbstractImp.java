package com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.imp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionOutOfRangeDate;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.StockEntry;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.grouped.StocksEntryGrouped;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.stocks.type.TypeStockEntry;

public abstract class StocksAbstractImp implements StocksEntryGrouped {

	private static Logger logger = LoggerFactory.getLogger( StocksAbstractImp.class );

	protected int[][] dateArrayIndexPlusOne = null;
	protected ArrayList<StockEntry> stockEntrys = new ArrayList<StockEntry>();
	protected HashMap<TypeStockEntry, ArrayList<StockEntry>> stockEntryHash = null;

	@Override
	public abstract List<StockEntry> getRelativeDateStockEntry(int date) throws ExceptionOutOfRangeDate;

	@Override
	public abstract List<StockEntry> getRelativeDateStockEntry(int date, TypeStockEntry stockEntry) throws ExceptionOutOfRangeDate;

	@Override
	public abstract List<List<StockEntry>> getRelativeDateStockEntry(int[] date) throws ExceptionOutOfRangeDate;

	@Override
	public abstract List<List<StockEntry>> getRelativeDateStockEntry(int[] date, TypeStockEntry stockEntry) throws ExceptionOutOfRangeDate;

	@Override
	public abstract List<StockEntry> getRelativeUntilDateStockEntries(int date, TypeStockEntry stockEntryType) throws ExceptionOutOfRangeDate;;

	@Override
	public StockEntry get(int index) {
		return get(index, null);
	}

	@Override
	public String getIdent() {
		return get(0, null).getStock().getStock();
	}

	@Override
	public StockEntry get(int index, TypeStockEntry type) {
		if(type == null)
			return stockEntrys.get(index);
		if(stockEntryHash == null) {
			createHash();
		}
		return stockEntryHash.get(type).get(index);
	}

	@Override
	public void add(StockEntry stockEntry) {
		synchronized (this) {
			stockEntrys.add(stockEntry);
			stockEntryHash = null;
			dateArrayIndexPlusOne = null;
		}
	}

	@Override
	public int[] getDateArray() {
		return getDateArray(null);
	}

	@Override
	public int[] getDateArray(TypeStockEntry type) {
		synchronized (this) {
			if(logger.isDebugEnabled())
				logger.debug(String.format("getDateArray will getting the date array for TypeStockEntry [%s]", type == null ? "null" : type));
			if(dateArrayIndexPlusOne != null) {
				if(logger.isDebugEnabled())
					logger.debug(String.format("getDateArray will returnig [%s]", Arrays.toString((type == null) ? dateArrayIndexPlusOne[0] : removeNulls(dateArrayIndexPlusOne[type.getIntType()+1]))));
				return (type == null) ? dateArrayIndexPlusOne[0] : removeNulls(dateArrayIndexPlusOne[type.getIntType()+1]);
			}
			ArrayList<Integer> dateListLocal = new ArrayList<Integer>();
			for (int i = 0; i < stockEntrys.size(); i++) {
				if(!dateListLocal.contains(get(i).getDate()))
					dateListLocal.add(get(i).getDate());
			}
			Collections.sort(dateListLocal);
			dateArrayIndexPlusOne = new int[TypeStockEntry.values().length+1][dateListLocal.size()];
			for (int i = 0; i < dateArrayIndexPlusOne.length; i++) {
				Arrays.fill(dateArrayIndexPlusOne[i], -1);
			}
			for (int i = 0; i < dateListLocal.size(); i++)
				dateArrayIndexPlusOne[0][i] = dateListLocal.get(i);
			dateListLocal.clear();
			for (int k = 0; k < TypeStockEntry.values().length; k++) {
				for (int i = 0; i < stockEntrys.size(); i++)
					if(get(i).getType().getIntType() == TypeStockEntry.values()[k].getIntType())
						if(!dateListLocal.contains(get(i).getDate()))
							dateListLocal.add(get(i).getDate());
				Collections.sort(dateListLocal);
				for (int l = 0; l < dateListLocal.size(); l++) {
					dateArrayIndexPlusOne[
					                      TypeStockEntry.
					                      values()[k].
					                      getIntType()+1][l] = dateListLocal.get(l);
				}
				dateListLocal.clear();
			}
			return (type == null) ? dateArrayIndexPlusOne[0] : removeNulls(dateArrayIndexPlusOne[type.getIntType()+1]);
		}
	}

	protected int[] removeNulls(int[] array) {
		int countNotNulls = 0;
		for (int i = 0; i < array.length; i++)
			if(array[i] >= 0)
				countNotNulls++;
		int[] newArray = new int[countNotNulls];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = array[i];
		}
		return newArray;
	}

	protected int rotateDate(int date) throws ExceptionOutOfRangeDate {
		return rotateDate(date, null);
	}

	protected int rotateDate(int date, TypeStockEntry type) throws ExceptionOutOfRangeDate {
		if(logger.isDebugEnabled())
			logger.debug(String.format("rotateDate will use the following date array [%s]", Arrays.toString(getDateArray(type))));
		if(getDateArray(type) == null || getDateArray(type).length == 0 || getDateArray(type)[0] >  date) {
			if(logger.isDebugEnabled())
				logger.debug(String.format("rotateDate has a date above the array [%s>%s:%s]", getDateArray(type) == null || getDateArray(type).length == 0 ? null : getDateArray(type)[0],  date, type == null ? "null" : type));
			throw new ExceptionOutOfRangeDate(date);
		}
		//The next part didnt consider other type of entries
		if(getDateArray(type)[getDateArray(type).length-1] <  date) {
			if(logger.isDebugEnabled())
				logger.debug(String.format("rotateDate has a date above the array [%s<%s:%s]", getDateArray(type)[getDateArray(type).length-1],  date, type == null ? "null" : type));
			return getDateArray(type)[getDateArray(type).length-1];
		}
		int index =  Arrays.binarySearch(getDateArray(type), date);
		index = (index < 0) ? -index-2 : index;
		if(logger.isDebugEnabled()) {
			logger.debug(String.format("rotateDate binarysearch returned index [%s]", index));
			logger.debug(String.format("rotateDate binarysearch returned [%s:%s]", date, getDateArray(type)[index]));
		}
		return index < 0 ?  getDateArray(type)[-index-2] : getDateArray(type)[index];
	}
	
	protected List<Integer> rotateUntilDate(int date, TypeStockEntry type) throws ExceptionOutOfRangeDate {
		if(logger.isDebugEnabled())
			logger.debug(String.format("rotateUntilDate will use the following date array [%s]", Arrays.toString(getDateArray(type))));
		ArrayList<Integer> dates = new ArrayList<Integer>();
		try {
			dates.add(rotateDate(date, type));
			while(true) {
				dates.add(rotateDate(dates.get(dates.size()-1)-1, type));
			}
		}catch(ExceptionOutOfRangeDate e){
			return dates;
		}
	}

	@Override
	public String toString() {
		//TODO not prepared yet
		if(stockEntrys.size()<1)
			return "";
		StringBuilder str = new StringBuilder();
		str.append("{");
		str.append(get(0));
		for (int i = 1; i < stockEntrys.size(); i++)
			str.append("}").append(get(i));
		str.append("}");
		return str.toString();
	}

	@Override
	public ArrayList<StockEntry> sort() {
		return sort(null);
	}

	@Override
	public ArrayList<StockEntry> sort(TypeStockEntry type) {
		//TODO We can enhance efficiency by removing items already sorted
		ArrayList<StockEntry> localList = new ArrayList<StockEntry>();
		for (int i = 0; i < getDateArray().length; i++)
			for (int j = 0; j < dateArrayIndexPlusOne.length; j++)
				if(getDateArray()[i] == get(j,type).getDate())
					localList.add(get(j,type));
		return localList;
	}

	protected void createHash() {
		stockEntryHash = new HashMap<TypeStockEntry,ArrayList<StockEntry>>();
		for (int i = 0; i < stockEntrys.size(); i++) {
			if(!stockEntryHash.containsKey(stockEntrys.get(i).getType()))
				stockEntryHash.put(stockEntrys.get(i).getType(), new ArrayList<StockEntry>());
			stockEntryHash.get(stockEntrys.get(i).getType()).add(stockEntrys.get(i));
		}
	}

}
