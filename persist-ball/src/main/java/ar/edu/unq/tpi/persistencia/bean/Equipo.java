package ar.edu.unq.tpi.persistencia.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ar.edu.unq.tpi.persistencia.interfaces.Nombrable;
import ar.edu.unq.tpi.persistencia.logic.Formacion;

@Entity
public class Equipo extends PersistentObject implements Nombrable{
	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade= CascadeType.ALL)
	private Tecnico tecnico;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "equipo_id")
	private List<Jugador> jugadores = new ArrayList<Jugador>();
	
	@Basic
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
