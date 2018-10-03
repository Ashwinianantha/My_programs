package com.acis.collections1;

import java.util.*;

class TestSort {
	public static void main(String args[]) {

		ArrayList<String> al = new ArrayList<String>();
		al.add("Viru");
		al.add("Saurav");
		al.add("Mukesh");
		al.add("Tahir");

		Collections.sort(al);
		Iterator itr = al.iterator();
		for (String str : al) {
			System.out.println(str);
		}

	}
}