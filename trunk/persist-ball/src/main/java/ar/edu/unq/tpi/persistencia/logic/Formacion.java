package ar.edu.unq.tpi.persistencia.logic;

import java.util.List;

import ar.edu.unq.tpi.persistencia.bean.Equipo;
import ar.edu.unq.tpi.persistencia.bean.Jugador;
import ar.edu.unq.tpi.persistencia.bean.Titular;

public class Formacion {
	private List<Titular> titulares;
	private List<Jugador> suplentes;
	private Equipo equipo;
	
	public List<Titular> getTitulares() {
		return titulares;
	}
	public void setTitulares(List<Titular> titulares) {
		this.titulares = titulares;
	}
	public List<Jugador> getSuplentes() {
		return suplentes;
	}
	public void setSuplentes(List<Jugador> suplentes) {
		this.suplentes = suplentes;
	}
	public Equipo getEquipo() {
		return equipo;
	}
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

}
