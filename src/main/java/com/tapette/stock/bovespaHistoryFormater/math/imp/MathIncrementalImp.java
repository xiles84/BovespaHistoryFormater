package com.tapette.stock.bovespaHistoryFormater.math.imp;

import java.util.ArrayList;
import java.util.Arrays;

import com.tapette.stock.bovespaHistoryFormater.math.StrockMath;

public class MathIncrementalImp implements StrockMath {
	
	MathImp math = null;

	public MathIncrementalImp(double[][] matrix) {
		double[][] matrixLocal = new double[matrix.length][matrix[0].length];
		for (int i = 0; i < matrixLocal.length; i++) {
			Arrays.fill(matrixLocal[i],-1d);
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if(i>=1 && matrix[i][j] >= 0) {
					if(matrix[i-1][j]>=0) {
						matrixLocal[i][j]=(matrix[i][j]-matrix[i-1][j])/matrix[i-1][j];
					}else {
						matrixLocal[i][j]=0;
					}
				}else if(matrix[i][j] >= 0) {
					matrixLocal[i][j] = 0;
				}
			}
		}
		math = new MathImp(matrixLocal);
	}

	@Override
	public double[] getMeans() {
		return this.math.getMeans();
	}

	@Override
	public double[][] getSimpleCovariance() throws Exception {
		return this.math.getSimpleCovariance();
	}

	@Override
	public double[][] getPonderedCovariance(int[] ponder) throws Exception {
		return this.math.getPonderedCovariance(ponder);
	}

	@Override
	public double[][] getMatrix() {
		return this.math.getMatrix();
	}

	@Override
	public double[] getPonderedMeans(int[] ponder) {
		return this.math.getPonderedMeans(ponder);
	}

}
