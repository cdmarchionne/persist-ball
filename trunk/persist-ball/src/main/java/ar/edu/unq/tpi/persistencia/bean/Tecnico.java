package ar.edu.unq.tpi.persistencia.bean;

import ar.edu.unq.tpi.persistencia.interfaces.FormacionStrategy;
import ar.edu.unq.tpi.persistencia.interfaces.Nombrable;
import ar.edu.unq.tpi.persistencia.logic.Formacion;

public class Tecnico implements Nombrable{
	
	private FormacionStrategy formacionStrategy;
	
	@Override
	public String getNombre() {
		return null;
	}

	public Formacion armarFormacion(Equipo equipo) {
		return formacionStrategy.armarFormacion(equipo);
	}

}
