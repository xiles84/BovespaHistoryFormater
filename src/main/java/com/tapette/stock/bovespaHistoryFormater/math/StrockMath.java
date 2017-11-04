package com.tapette.stock.bovespaHistoryFormater.math;

import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionOutOfRange;

public interface StrockMath {

	public double[] getMeans();

	public double[][] getSimpleCovariance() throws ExceptionOutOfRange;

	public double[][] getPonderedCovariance(int[] ponder) throws ExceptionOutOfRange;

	public double[][] getMatrix();

	public double[] getPonderedMeans(int[] ponder);

}
