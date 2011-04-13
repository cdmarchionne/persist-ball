package ar.edu.unq.tpi.persistencia.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import ar.edu.unq.tpi.persistencia.enums.Posicion;
import ar.edu.unq.tpi.persistencia.interfaces.Habilidad;
import ar.edu.unq.tpi.persistencia.interfaces.Nombrable;

@Entity
@Table(name="Jugador")
public class Jugador extends PersistentObject implements Nombrable{
	private static final long serialVersionUID = 351122077442453571L;

	@OneToMany(orphanRemoval=true, cascade=CascadeType.ALL)
    @JoinColumn(name = "jugador_id")
	private List<Habilidad> habilidades = new ArrayList<Habilidad>();
	
	@Basic
	private String nombre;
	
	@OneToOne(cascade=CascadeType.ALL)
	private Equipo equipo;

	@Override
	public String getNombre() {
		return nombre;
	}
	
	public Jugador(){}
	
	public Jugador(String nombre) {
		super();
		this.setNombre(nombre);
	}

	public void addHabilidad(Habilidad habilidad){
		this.getHabilidades().add(habilidad);
	}
	
	public int getValorHabilidad(Posicion posicion) {
	    int max=0;
	    for (Habilidad habilidad : this.getHabilidades()) {
	        if(habilidad.getValor(posicion) > max){
	            max= habilidad.getValor(posicion);
	        }
        }
		return max;
	}

	public void setHabilidades(List<Habilidad> habilidades) {
		this.habilidades = habilidades;
	}

	public List<Habilidad> getHabilidades() {
		return habilidades;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Equipo getEquipo() {
		return equipo;
	}

}
