package com.tapette.stock.bovespaHistoryFormater.math.imp;

import static org.junit.Assert.*;

import java.util.Arrays;

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
	public void testMathIncrementalImp() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMeans() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetSimpleCovariance() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPonderedCovariance() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMatrix() {
		for (int i = 0; i < matrix.length; i++) {
			System.out.println(Arrays.toString(math.getMatrix()[i]));
		}
//		fail("Not yet implemented");
	}

	@Test
	public void testGetPonderedMeans() {
		fail("Not yet implemented");
	}

}
