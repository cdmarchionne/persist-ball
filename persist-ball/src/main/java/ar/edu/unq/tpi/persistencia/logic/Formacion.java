package ar.edu.unq.tpi.persistencia.logic;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import ar.edu.unq.tpi.persistencia.bean.Equipo;
import ar.edu.unq.tpi.persistencia.bean.Jugador;
import ar.edu.unq.tpi.persistencia.bean.PersistentObject;
import ar.edu.unq.tpi.persistencia.bean.Titular;

@Entity
public class Formacion extends PersistentObject{
	private static final long serialVersionUID = 1L;

	@OneToMany(cascade=CascadeType.ALL)
	private List<Titular> titulares;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Jugador> suplentes;
	
	@OneToOne(cascade=CascadeType.ALL)
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
