package ar.edu.unq.tpi.persistencia.bean;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.tpi.persistencia.enums.Posicion;
import ar.edu.unq.tpi.persistencia.interfaces.Habilidad;
import ar.edu.unq.tpi.persistencia.interfaces.Nombrable;

public class Jugador implements Nombrable{
	private List<Habilidad> habilidades = new ArrayList<Habilidad>();
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
