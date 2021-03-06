package com.tapette.stock.bovespaHistoryFormater.math.imp;

import java.util.Arrays;

import com.tapette.stock.bovespaHistoryFormater.exceptions.ExceptionOutOfRange;
import com.tapette.stock.bovespaHistoryFormater.math.StrockMath;

public class MathImp implements StrockMath{

	private double[][] matrix = null;
	private double[] means = null;
	private double[] meansUp = null;
	private double[] meansDown = null;
	private int[] ponderMean = null;
	private int[] ponderCov = null;
	private double[][] cov = null;
	private double[][] covUp = null;
	private double[][] covDown = null;
	

	public MathImp(double[][] matrix) {
		this.matrix = matrix;
	}

	@Override
	public double[] getMeans() {
		if(this.means != null) return this.means;
		int[] ponder = new int[matrix.length];
		Arrays.fill(ponder, 1);
		getPonderedMeans(ponder);
		return this.means;
	}
	
	@Override
	public double[] getPonderedMeans(int[] ponder) {
		if(this.means != null &&
				this.ponderMean != null &&
				Arrays.equals(ponder,this.ponderMean))
			return this.means;
		this.ponderMean = ponder;
		int[] counter = new int[matrix[0].length];
		this.means = new double[matrix[0].length];
		this.meansUp = new double[matrix[0].length];
		this.meansDown = new double[matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if(matrix[i][j] >= 0) {
					counter[j] = counter[j] + ponder[i];
					this.means[j] = this.means[j] + ponder[i] * matrix[i][j];
//					System.out.println("counter[" + j + "] = " + counter[j] + "----" + matrix[i][j]);
				}
			}
		}
		for (int i = 0; i < this.means.length; i++) {
			if(counter[i] <= 0) {
				this.means[i] = -1;
				this.means[i] = -1;
				this.means[i] = -1;
			}
			else {
				this.meansUp[i] = means[i];
				this.meansDown[i] = counter[i];
				this.means[i] = means[i]/counter[i];
			}
		}
		return this.means;
	}

	@Override
	public double[][] getSimpleCovariance() throws ExceptionOutOfRange {
		int[] ponder = new int[matrix.length];
		Arrays.fill(ponder, 1);
		getPonderedCovariance(ponder);
		return this.cov;
	}

	@Override
	public double[][] getPonderedCovariance(int[] ponder) throws ExceptionOutOfRange {
		if(this.cov != null &&
				this.ponderCov != null &&
				Arrays.equals(ponder,this.ponderCov))
			return this.cov;
		this.ponderCov = ponder;
		if(ponder.length < matrix.length) throw new ExceptionOutOfRange("The ponder size [" + ponder.length + "] is less than the matrix [" + matrix.length + "]");
		int[][] counter = new int[getMeans().length][getMeans().length];
		int[][] counterMean = new int[getMeans().length][getMeans().length];
		double[][] localMeans = new double[getMeans().length][getMeans().length];
		this.cov = new double[getMeans().length][getMeans().length];
		this.covUp = new double[getMeans().length][getMeans().length];
		this.covDown = new double[getMeans().length][getMeans().length];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				for (int k = 0; k < matrix[i].length; k++) {
					if(matrix[i][j] >=0 && matrix[i][k] >= 0) {
						localMeans[j][k] = localMeans[j][k] + ponder[i] * matrix[i][j];
						counterMean[j][k] = counterMean[j][k] + ponder[i];
					}
				}
			}
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				for (int k = j; j <= k && k < matrix[i].length; k++) {
					if(matrix[i][j] >=0 && matrix[i][k] >= 0) {
						this.cov[j][k] = this.cov[j][k] + ponder[i] * (matrix[i][j] * counterMean[j][k] - localMeans[j][k]) * (matrix[i][k] * counterMean[k][j] - localMeans[k][j]) / (counterMean[j][k] * counterMean[k][j]);
						counter[j][k] = counter[j][k] + ponder[i];
					}
				}
			}
		}
		for (int i = 0; i < this.cov.length; i++) {
			for (int j = 0; j < this.cov[i].length; j++) {
				if(counter[i][j] <= 1) {
					this.covUp[i][j] = -1;
					this.covUp[i][j] = -1;
					this.cov[i][j] = -1;
				}
				else {
					this.covUp[i][j] = this.cov[i][j];
					this.covDown[i][j] = counter[i][j];
					this.cov[i][j] = this.cov[i][j]/(counter[i][j]-1);
				}
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
