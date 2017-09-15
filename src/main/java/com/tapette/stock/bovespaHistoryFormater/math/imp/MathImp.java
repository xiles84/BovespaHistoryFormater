package com.tapette.stock.bovespaHistoryFormater.math.imp;

import java.util.Arrays;

import com.tapette.stock.bovespaHistoryFormater.math.StrockMath;

public class MathImp implements StrockMath{

	private double[][] matrix = null;
	private double[] means = null;
	private double[][] cov = null;

	public MathImp(double[][] matrix) {
		this.matrix = matrix;
	}

	@Override
	public double[] getMeans() {
		if(this.means != null) return this.means;
		int[] counter = new int[matrix[0].length];
		this.means = new double[matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if(matrix[i][j] >= 0) {
					counter[j]++;
					this.means[j] = this.means[j] + matrix[i][j];
				}
			}
		}
		for (int i = 0; i < this.means.length; i++) {
			if(counter[i] <= 0)
				this.means[i] = -1;
			else
				this.means[i] = means[i]/counter[i];
		}
		return this.means;
	}

	@Override
	public double[][] getSimpleCovariance() throws Exception {
		if(this.cov != null) return this.cov;
		int[] ponder = new int[matrix.length];
		Arrays.fill(ponder, 1);
		getPonderedCovariance(ponder);
		return this.cov;
	}

	@Override
	public double[][] getPonderedCovariance(int[] ponder) throws Exception {
		if(ponder.length < matrix.length) throw new Exception("The ponder size [" + ponder.length + "] is less thab the matrix [" + matrix.length + "]");
		int[][] counter = new int[getMeans().length][getMeans().length];
		this.cov = new double[getMeans().length][getMeans().length];
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++)
				for (int k = j; j <= k && k < matrix[i].length; k++)
					if(matrix[i][j] >=0 && matrix[i][k] >= 0) {
						this.cov[j][k] = ponder[i] * (matrix[i][j] - this.means[j]) * (matrix[i][k] - this.means[k]);
						counter[j][k] = counter[j][k] + ponder[i];
					}
		for (int i = 0; i < this.cov.length; i++) {
			for (int j = 0; j < this.cov[i].length; j++) {
				if(counter[i][j] <= 1)
					this.cov[i][j] = -1;
				else
					this.cov[i][j] = this.cov[i][j]/(counter[i][j]-1);
			}
		}
		for (int i = 1; i < this.cov.length; i++)
			for (int j = 0; j < i; j++)
				this.cov[i][j] = this.cov[j][i];
		return this.cov;
	}

	@Override
	public double[][] getMatrix() {
		return matrix;
	}



}
