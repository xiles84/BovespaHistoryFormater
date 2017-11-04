package com.tapette.stock.bovespaHistoryFormater.inputs.extracters;

import java.io.IOException;
import java.util.ArrayList;

import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionEmptyFile;
import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionInvalidFormat;
import com.tapette.stock.bovespaHistoryFormater.inputs.table.TableDAO;

public interface Extracters {

	public boolean execute() throws IOException, ExceptionEmptyFile, ExceptionInvalidFormat;

	public TableDAO getList() throws IOException, ExceptionEmptyFile, ExceptionInvalidFormat, InterruptedException;

	public ArrayList<String> getFileDir();

}
