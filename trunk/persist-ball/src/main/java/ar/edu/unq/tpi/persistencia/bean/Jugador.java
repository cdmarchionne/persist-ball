package ar.edu.unq.tpi.persistencia.bean;

import java.util.List;

import ar.edu.unq.tpi.persistencia.enums.Posicion;
import ar.edu.unq.tpi.persistencia.interfaces.Habilidad;
import ar.edu.unq.tpi.persistencia.interfaces.Nombrable;

public class Jugador implements Nombrable, Habilidad{
	private List<Habilidad> habilidades;

	@Override
	public String getNombre() {
		return null;
	}
	
	@Override
	public int getValor(Posicion posicion) {
		return 0;
	}

}
