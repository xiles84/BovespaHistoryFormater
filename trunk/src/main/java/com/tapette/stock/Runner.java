package com.tapette.stock;

import com.tapette.stock.file.Formater;

public class Runner {

	public static void main(String[] args) {
		Formater form = new Formater();
		form.setFileStr("C:\\Users\\Xiles84\\Downloads\\COTAHIST_A2017\\COTAHIST_A2017.TXT");
		try {
			form.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
