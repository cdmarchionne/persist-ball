package ar.edu.unq.tpi.persistencia.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import ar.edu.unq.tpi.persistencia.enums.Posicion;
import ar.edu.unq.tpi.persistencia.interfaces.Habilidad;
import ar.edu.unq.tpi.persistencia.interfaces.Nombrable;

@javax.persistence.Entity
public class Jugador extends Entity implements Nombrable{
    
    @OneToMany
    @Cascade({ CascadeType.ALL, CascadeType.DELETE_ORPHAN })
    @Transient
	private List<Habilidad> habilidades = new ArrayList<Habilidad>();
	
	
	@Basic
	private String nombre;

	@Override
	public String getNombre() {
		return nombre;
	}
	
	public Jugador(){}
	
	public Jugador(String nombre) {
		super();
		this.nombre = nombre;
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

}
