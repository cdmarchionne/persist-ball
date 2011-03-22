package ar.edu.unq.tpi.utils;

import java.util.List;

import ar.edu.unq.tpi.persistencia.bean.Titular;

public class ListUtils {
	
	public static boolean compareLists(List<Titular> l1, List<Titular> l2){				
		return l1.containsAll(l2) && l1.size()== l2.size();
	}
}
