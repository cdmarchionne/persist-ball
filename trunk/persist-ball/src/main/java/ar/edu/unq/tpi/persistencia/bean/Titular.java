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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jugador == null) ? 0 : jugador.hashCode());
		result = prime * result
		+ ((posicion == null) ? 0 : posicion.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Titular other = (Titular) obj;
		if (jugador == null) {
			if (other.jugador != null)
				return false;
		} else if (!jugador.equals(other.jugador))
			return false;
		if (posicion != other.posicion)
			return false;
		return true;
	}
	
	public String toString(){
		return "Jugador: " + this.jugador.getNombre() + " Pos: " + this.posicion ;
	}
}
