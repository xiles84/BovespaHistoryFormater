package com.tapette.stock.bovespaHistoryFormater.math.imp;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;

public class MathImpTest {
	
	static MathImp math = null;
	static double[][] matrix = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		matrix = new double[4][4];
		matrix[0] = new double[]{1 , 1 , 1 , 3};
		matrix[1] = new double[]{2 , 3 , 1 , 5};
		matrix[2] = new double[]{3 , 5 , 1 , 6};
		matrix[3] = new double[]{4 , 7 , 1 , 7};
		
		math = new MathImp(matrix);
		
	}

	@Test
	public void testMathImp() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMeans() {
		double[] means = new double[] {2.5 , 4 , 1 , 5.25};
		if(math != null && !Arrays.equals(math.getMeans(),means))
			fail("Not working Expected X Got: {" + Arrays.toString(means) + "} X {" + Arrays.toString(math.getMeans()) + "}");
	}

	@Test
	public void testGetPonderedMeans() {
		int[] ponder = new int[] {1 , 2 , 3 , 4};
		double[] means = new double[] {3 , 5 , 1 , 5.9};
		if(math != null && !Arrays.equals(math.getPonderedMeans(ponder),means))
			fail("Not working Expected X Got: {" + Arrays.toString(means) + "} X {" + Arrays.toString(math.getPonderedMeans(ponder)) + "}");
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
		fail("Not yet implemented");
	}

}
