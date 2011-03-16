package ar.edu.unq.tpi.persistencia.bean;

import ar.edu.unq.tpi.persistencia.enums.Posicion;

public class Titular {
	private Jugador jugador;
	private Posicion posicion;
	
	
	public Titular(Jugador jugador, Posicion posicion2) {
		this.jugador = jugador;
		this.posicion = posicion2;
	}
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	public Posicion getPosicion() {
		return posicion;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	public Jugador getJugador() {
		return jugador;
	}

}
