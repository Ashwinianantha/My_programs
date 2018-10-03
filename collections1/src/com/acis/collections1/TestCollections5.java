package com.acis.collections1;

import java.util.*;

class TestCollections5 {
	public static void main(String args[]) {

		ArrayList<String> al = new ArrayList<String>();
		al.add("Rav");
		al.add("Vijay");
		al.add("Ajay");

		ArrayList<String> al2 = new ArrayList<String>();
		al2.add("Rav");
		al2.add("Hanumat");
		al2.add("hufdhu");
		al2.retainAll(al2);

		System.out.println("iterating the elements after removing the elements of al2...");
		for (String str : al2) {
			System.out.println(str);

		}

	}
}
