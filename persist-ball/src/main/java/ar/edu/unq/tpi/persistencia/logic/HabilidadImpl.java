package ar.edu.unq.tpi.persistencia.logic;

import ar.edu.unq.tpi.persistencia.enums.Posicion;
import ar.edu.unq.tpi.persistencia.interfaces.Habilidad;

public class HabilidadImpl implements Habilidad {
	private Posicion posicion;
	private int valor;

	
	@Override
	public int getValor(Posicion posicion) {
		return 0;
	}

	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}

	public Posicion getPosicion() {
		return posicion;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getValor() {
		return valor;
	}

}
