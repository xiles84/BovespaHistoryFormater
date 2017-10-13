package com.tapette.stock.bovespaHistoryFormater.math;

public interface StrockMath {

	public double[] getMeans();

	public double[][] getSimpleCovariance() throws Exception;

	public double[][] getPonderedCovariance(int[] ponder) throws Exception;

	public double[][] getMatrix();

	public double[] getPonderedMeans(int[] ponder);

}
