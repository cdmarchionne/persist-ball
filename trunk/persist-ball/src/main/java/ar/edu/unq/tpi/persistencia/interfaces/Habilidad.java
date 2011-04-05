package ar.edu.unq.tpi.persistencia.interfaces;

import javax.persistence.MappedSuperclass;

import ar.edu.unq.tpi.persistencia.enums.Posicion;

@MappedSuperclass
public interface Habilidad {

	public int getValor(Posicion posicion);

}
