package com.tapette.stock.bovespaHistoryFormater.math.imp;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

public class MathIncrementalImpTest {
	
	static MathIncrementalImp math = null;
	static double[][] matrix = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		matrix = new double[4][5];
		matrix[0] = new double[]{1 , 1 , 1 , 3 , -1};
		matrix[1] = new double[]{2 , 3 , 1 , 5 , -1};
		matrix[2] = new double[]{3 , 5 , 1 , 6 ,  3};
		matrix[3] = new double[]{4 , 7 , 1 , 7 ,  10};
		math = new MathIncrementalImp(matrix);
	}

	@Test
	public void testGetMatrix() {
		 double[][] matrixLocal = new double[4][5];
		matrixLocal[0] = new double[]{0d , 0d , 0d , 0d , -1};
		matrixLocal[1] = new double[]{1d , (3d-1d)/1d , 0d , (5d-3d)/3d , -1};
		matrixLocal[2] = new double[]{(3d-2d)/2d , (5d-3d)/(3d) , 0d , (6d-5d)/(5d) ,  0};
		matrixLocal[3] = new double[]{(4d-3d)/(3d) , (7d-5d)/(5d) , 0d , (7d-6d)/(6d) ,  (10d-3d)/3d};
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if(matrixLocal[i][j] != math.getMatrix()[i][j])
					fail("matrixLocal[" + i + "][" + j + "] != matrix[" + i + "][" + j + "] | (" + matrixLocal[i][j] + ") (" + math.getMatrix()[i][j] + ")");
			}
		}
	}

}
