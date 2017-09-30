package com.tapette.stock.bovespaHistoryFormater.math.imp;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

public class TupleImpTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test1() {
		
	}
	
	@Test
	public void test2() {
		ArrayList<String> array = new ArrayList<String>();
		array.add("20061009");
		array.add("20061109");
		array.add("20070620");
		array.add("20071009");
		array.add("20081009");
		array.add("20090209");
		array.add("20091009");
		array.add("20101009");
		array.add("20121009");
		array.add("20160109");
		array.add("20171009");
		int[] touple = {1,1,2,2,3,5,5,8,13,21,34};
		
		assertArrayEquals(touple,TupleImp.getTuplet(array));
		
	}

}
