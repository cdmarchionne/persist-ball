package ar.edu.unq.tpi.persistencia.interfaces;

import ar.edu.unq.tpi.persistencia.bean.Equipo;
import ar.edu.unq.tpi.persistencia.logic.Formacion;

public interface FormacionStrategy {

	public Formacion armarFormacion(Equipo equipo);

}
