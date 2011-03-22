package ar.edu.unq.tpi.persistencia.bean;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.tpi.persistencia.enums.Posicion;
import ar.edu.unq.tpi.persistencia.interfaces.Habilidad;
import ar.edu.unq.tpi.persistencia.interfaces.Nombrable;

public class Jugador implements Nombrable{
	private List<Habilidad> habilidades = new ArrayList<Habilidad>();

	@Override
	public String getNombre() {
		return null;
	}
	
	public int getValorHabilidad(Posicion posicion) {
	    int max=0;
	    for (Habilidad habilidad : this.habilidades) {
	        if(habilidad.getValor(posicion) > max){
	            max= habilidad.getValor(posicion);
	        }
        }
		return max;
	}

}
