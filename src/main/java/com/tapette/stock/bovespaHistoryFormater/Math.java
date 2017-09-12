package com.tapette.stock.bovespaHistoryFormater;

public class Math {
	
	private double[][] matrix = null;
	private double[] means = null;
	private double[][] cov = null;
	private double[][] inc = null;
	
	public Math(double[][] matrix) {
		this.matrix = matrix;
	}
	
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
	
	public double[][] getSimpleCovariance() {
		if(this.cov != null) return this.cov;
		int[][] counter = new int[getMeans().length][getMeans().length];
		this.cov = new double[getMeans().length][getMeans().length];
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[i].length; j++)
				for (int k = j; j <= k && k < matrix[i].length; k++)
					if(matrix[i][j] >=0 && matrix[i][k] >= 0) {
						this.cov[j][k] = (matrix[i][j] - this.means[j]) * (matrix[i][k] - this.means[k]);
						counter[j][k]++;
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
	
	public double[][] getIncrements() {
		this.inc = new double[matrix.length][matrix[0].length];
		for (int i = 0; i < matrix[0].length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if(matrix[j][i] >= 0 && j >= 0) {
					if(j > 0 && matrix[j-1][i] > 0)
						this.inc[j][i] = (matrix[j][i] - matrix[j-1][i]) / matrix[j-1][i];
					else
						this.inc[j][i] = 0;
				}else
					this.inc[j][i] = -1;
			}
		}
		return this.inc;
	}

}
