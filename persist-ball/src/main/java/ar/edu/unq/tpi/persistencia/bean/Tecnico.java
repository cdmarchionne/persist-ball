package ar.edu.unq.tpi.persistencia.bean;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import ar.edu.unq.tpi.persistencia.interfaces.FormacionStrategy;
import ar.edu.unq.tpi.persistencia.interfaces.Nombrable;
import ar.edu.unq.tpi.persistencia.logic.Formacion;

@Entity
public class Tecnico extends PersistentObject implements Nombrable{
	private static final long serialVersionUID = -7283264065297827705L;

	@OneToOne
	private FormacionStrategy formacionStrategy;
	
	@Basic
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
