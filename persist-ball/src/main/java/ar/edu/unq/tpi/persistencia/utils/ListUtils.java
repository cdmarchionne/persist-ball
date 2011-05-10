package ar.edu.unq.tpi.persistencia.utils;

import java.util.List;

import ar.edu.unq.tpi.persistencia.bean.Titular;
import ar.edu.unq.tpi.persistencia.logic.Formacion;

public class ListUtils {
	
	public static boolean compareLists(List<?> l1, List<?> l2){				
		return l1.containsAll(l2) && l1.size()== l2.size();
	}
	
	
	public static Object random(List<?> list){	
		return random(list.toArray());
	}
	
	public static Object random(Object... objects){
		return objects[(int) (Math.random()*objects.length)];
	}
	
	
	
	public static boolean titularesNoEnSuplentes(Formacion aFormation){

		for(Titular t: aFormation.getTitulares()){
			if (aFormation.getSuplentes().contains(t))
				return false;
		}

		return true;
	}
}
