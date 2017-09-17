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
		double[][] covariance = new double[4][4];
		covariance[0] = new double[]{5d/3d , 10d/3d , 0 , 6.5d/3d};
		covariance[1] = new double[]{10d/3d , 20d/3d , 0 , 13d/3d};
		covariance[2] = new double[]{0 , 0 , 0 , 0};
		covariance[3] = new double[]{6.5d/3d , 13d/3d , 0 , 8.75d/3d};
		try {
			if(math != null && !Arrays.equals(math.getSimpleCovariance()[0],covariance[0]))
				fail("Not working[0] Expected X Got: {" + Arrays.toString(covariance[0]) + "} X {" + Arrays.toString(math.getSimpleCovariance()[0]) + "}");
			if(math != null && !Arrays.equals(math.getSimpleCovariance()[1],covariance[1]))
				fail("Not working[1] Expected X Got: {" + Arrays.toString(covariance[1]) + "} X {" + Arrays.toString(math.getSimpleCovariance()[1]) + "}");
			if(math != null && !Arrays.equals(math.getSimpleCovariance()[2],covariance[2]))
				fail("Not working[2] Expected X Got: {" + Arrays.toString(covariance[2]) + "} X {" + Arrays.toString(math.getSimpleCovariance()[2]) + "}");
			if(math != null && !Arrays.equals(math.getSimpleCovariance()[3],covariance[3]))
				fail("Not working[3] Expected X Got: {" + Arrays.toString(covariance[3]) + "} X {" + Arrays.toString(math.getSimpleCovariance()[3]) + "}");
		} catch (Exception e) {
			fail("entered the exception: " + e);
		}
	}

	@Test
	public void testGetPonderedCovariance() {
		int[] ponder = new int[] {1 , 2 , 3 , 4};
		double[][] covariance = new double[4][4];
		covariance[0] = new double[]{12.5d/9d , 25d/9d , 0 , 15.25d/9d};
		covariance[1] = new double[]{25d/9d , 50d/9d , 0 , 30.5d/9d};
		covariance[2] = new double[]{0 , 0 , 0 , 0};
		covariance[3] = new double[]{15.25d/9d , 30.5d/9d , 0 , 19.125d/9d};
		try {
			if(math != null && !Arrays.equals(math.getPonderedCovariance(ponder)[0],covariance[0]))
				fail("Not working[0] Expected X Got: {" + Arrays.toString(covariance[0]) + "} X {" + Arrays.toString(math.getSimpleCovariance()[0]) + "}");
			if(math != null && !Arrays.equals(math.getPonderedCovariance(ponder)[1],covariance[1]))
				fail("Not working[1] Expected X Got: {" + Arrays.toString(covariance[1]) + "} X {" + Arrays.toString(math.getSimpleCovariance()[1]) + "}");
			if(math != null && !Arrays.equals(math.getPonderedCovariance(ponder)[2],covariance[2]))
				fail("Not working[2] Expected X Got: {" + Arrays.toString(covariance[2]) + "} X {" + Arrays.toString(math.getSimpleCovariance()[2]) + "}");
			if(math != null && !Arrays.equals(math.getPonderedCovariance(ponder)[3],covariance[3]))
				fail("Not working[3] Expected X Got: {" + Arrays.toString(covariance[3]) + "} X {" + Arrays.toString(math.getSimpleCovariance()[3]) + "}");
		} catch (Exception e) {
			fail("entered the exception: " + e);
		}
		fail("Not yet implemented");
	}

	@Test
	public void testGetMatrix() {
		fail("Not yet implemented");
	}

}
