package com.tapette.stock.bovespaHistoryFormater.math;

public interface StrockMath {

	double[] getMeans();

	double[][] getSimpleCovariance() throws Exception;

	double[][] getPonderedCovariance(int[] ponder) throws Exception;

	double[][] getMatrix();

}
