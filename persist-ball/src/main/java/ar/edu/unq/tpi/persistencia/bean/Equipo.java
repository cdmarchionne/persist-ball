package ar.edu.unq.tpi.persistencia.bean;

import java.util.List;

import ar.edu.unq.tpi.persistencia.interfaces.Nombrable;
import ar.edu.unq.tpi.persistencia.logic.Formacion;

public class Equipo implements Nombrable{
	
	private Tecnico tecnico;
	private List<Jugador> jugadores;


	@Override
	public String getNombre() {
		return null;
	}

	public Formacion armarFormacion() {
		return tecnico.armarFormacion(this);
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}
	
	

}
