package ar.edu.unq.tpi.persistencia.bean;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.tpi.persistencia.interfaces.Nombrable;
import ar.edu.unq.tpi.persistencia.logic.Formacion;

public class Equipo implements Nombrable{
	
	private Tecnico tecnico;
	private List<Jugador> jugadores = new ArrayList<Jugador>();
	private String nombre;
	
	public Equipo() {
	}

	public Equipo(Tecnico tecnico, String nombre) {
		this();
		this.nombre = nombre;
		this.tecnico = tecnico;
	}
	

	@Override
	public String getNombre() {
		return nombre;
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
