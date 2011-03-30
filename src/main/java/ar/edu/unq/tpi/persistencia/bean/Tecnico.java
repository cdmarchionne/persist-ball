package ar.edu.unq.tpi.persistencia.bean;

import ar.edu.unq.tpi.persistencia.interfaces.FormacionStrategy;
import ar.edu.unq.tpi.persistencia.interfaces.Nombrable;
import ar.edu.unq.tpi.persistencia.logic.Formacion;

public class Tecnico implements Nombrable{
	
	private FormacionStrategy formacionStrategy;
	private String nombre;
	
	public Tecnico() {
	}
	
	public Tecnico(FormacionStrategy formacionStrategy, String nombre) {
		this.formacionStrategy = formacionStrategy;				
	}
	 
	@Override
	public String getNombre() {
		return nombre;
	}

	public Formacion armarFormacion(Equipo equipo) {
		return getFormacionStrategy().armarFormacion(equipo);
	}

	public void setFormacionStrategy(FormacionStrategy formacionStrategy) {
		this.formacionStrategy = formacionStrategy;
	}

	public FormacionStrategy getFormacionStrategy() {
		return formacionStrategy;
	}

}
