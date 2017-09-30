package com.tapette.stock.bovespaHistoryFormater.math.imp;

import java.util.List;

import com.tapette.stock.bovespaHistoryFormater.math.Tuplets;

public class TupleImp implements Tuplets{
	
	List<String> dates = null;
	
	public TupleImp(List<String> dates) {
		this.dates = dates;
	}

	@Override
	public int[] getTuplet() {
		return getTuplet(this.dates);
	}
	
	static public int[] getTuplet(List<String> dates) {
		int[] tuplet = new int[dates.size()];
		int[] fibbon = new int[3];
		String lastYear = new String();
		tuplet[0] = 1;
		fibbon[0] = 1; fibbon[1] = 1; fibbon[2] = 1;
		lastYear = dates.get(0).substring(0, 4);
		for (int i = 1; i < tuplet.length; i++) {
			if(!dates.get(i).substring(0, 4).equals(lastYear)) {
				lastYear = dates.get(i).substring(0, 4);
				tuplet[i] = fibbon[0] + fibbon[1];
				fibbon[2] = fibbon[0] + fibbon[1];
				fibbon[0] = fibbon[1];
				fibbon[1] = fibbon[2];
			}else {
				tuplet[i] = tuplet[i-1];
			}
		}
		return tuplet;
	}

}
