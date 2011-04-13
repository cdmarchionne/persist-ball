package ar.edu.unq.tpi.persistencia.interfaces;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.edu.unq.tpi.persistencia.bean.Equipo;
import ar.edu.unq.tpi.persistencia.bean.Jugador;
import ar.edu.unq.tpi.persistencia.bean.PersistentObject;
import ar.edu.unq.tpi.persistencia.enums.Posicion;
import ar.edu.unq.tpi.persistencia.logic.Formacion;

@Entity
@Table(name="FormacionStrategy")
public abstract class FormacionStrategy extends PersistentObject{
	private static final long serialVersionUID = 1L;
	
	
	@Cascade(CascadeType.ALL)
	@Enumerated
	@Column(name="posicion")
	@ElementCollection
	@OrderColumn(name="order_index")
	private List<Posicion> posiciones = new ArrayList<Posicion>();
	
	@OneToMany
    @Cascade({ CascadeType.ALL })
    @JoinColumn(name = "formacion_id")
	private List<Jugador> jugadores = new ArrayList<Jugador>();

	public abstract Formacion armarFormacion(Equipo equipo);

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setPosiciones(List<Posicion> posiciones) {
		this.posiciones = posiciones;
	}

	public List<Posicion> getPosiciones() {
		return posiciones;
	}

}
