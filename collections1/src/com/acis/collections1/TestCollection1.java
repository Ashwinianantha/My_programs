package com.acis.collections1;

import java.util.*;

class TestCollection1 {
	public static void main(String args[]) {
		ArrayList<String> list = new ArrayList<String>();// Creating arraylist
		list.add("Ravi");// Adding object in arraylist
		list.add("Vijay");
		list.add("Ravi");
		list.add("Ajay");

		for (String str : list)
			System.out.println(str);
	}
}