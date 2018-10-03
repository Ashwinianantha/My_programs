package com.acis.collections1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Hashmap {

	public static void main(String[] args) {
		Map<String,Integer> hm = new HashMap<String,Integer>();
		hm.put("Skk",11);
		hm.put("shg", 27);
		hm.put("shdgj", 29);
		Set<String> keys =hm.keySet();
		
		for(String key:keys)
			System.out.println(key+" "+hm.get(key) );
		
				

	}

}
