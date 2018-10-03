
package com.acis.collections1;

import java.util.*;

public class TestCollections3 {
	public static void main(String args[]) {
		// Creating user-defined class objects
		Student s1 = new Student(101, "Souygjh", 20);
		Student s2 = new Student(102, "Rgj", 21);
		Student s3 = new Student(103, "Hghf", 22);

		ArrayList<Student> al = new ArrayList<Student>();
		al.add(s1);// adding Student class object
		al.add(s2);
		al.add(s3);
		// Getting Iterator
		try {

			for (Student st : al) {
				System.out.println(st.rollno + " " + st.name + " " + st.age);
			}

			Iterator itr = al.iterator();
			// traversing elements of ArrayList object
			while (itr.hasNext()) {			
				Student st = (Student) itr.next();
				System.out.println(st.rollno + " " + st.name + " " + st.age);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}