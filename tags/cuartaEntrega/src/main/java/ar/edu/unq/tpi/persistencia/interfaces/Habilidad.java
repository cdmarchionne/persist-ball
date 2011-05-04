package ar.edu.unq.tpi.persistencia.interfaces;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import ar.edu.unq.tpi.persistencia.bean.PersistentObject;
import ar.edu.unq.tpi.persistencia.enums.Posicion;

@Entity
@Table(name="Habilidad")
public abstract class Habilidad extends PersistentObject {
	private static final long serialVersionUID = 1L;
	
    @Enumerated(EnumType.STRING)
	private Posicion posicion;
    @Basic
	private int valor;
    

	public abstract int getValor(Posicion posicion);


	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	protected int getValor() {
		return valor;
	}

}
