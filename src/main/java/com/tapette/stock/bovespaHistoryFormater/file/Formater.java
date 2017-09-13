package com.tapette.stock.bovespaHistoryFormater.file;

import com.tapette.stock.bovespaHistoryFormater.file.table.TableDAOImp;

public interface Formater {
	
	public boolean execute() throws Exception;

	public String getFileStr();

	public void setFileStr(String fileStr);

	public boolean isExecuted();

	public TableDAOImp getList() throws Exception;
}
