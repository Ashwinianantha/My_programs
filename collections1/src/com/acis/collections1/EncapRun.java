package com.acis.collections1;

public class EncapRun {
	public static void main(String args[]) {
		TestEncap encap = new TestEncap();
		encap.setMarks(45);
		encap.setName("skjdkjn");
		System.out.print("Name : " + encap.getName() +"    " +   "Marks : " + encap.getMarks());

	}
}