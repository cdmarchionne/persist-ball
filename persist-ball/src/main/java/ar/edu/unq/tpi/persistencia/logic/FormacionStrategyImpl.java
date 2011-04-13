package ar.edu.unq.tpi.persistencia.logic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

import ar.edu.unq.tpi.persistencia.bean.Equipo;
import ar.edu.unq.tpi.persistencia.bean.Jugador;
import ar.edu.unq.tpi.persistencia.bean.Titular;
import ar.edu.unq.tpi.persistencia.enums.Posicion;
import ar.edu.unq.tpi.persistencia.interfaces.FormacionStrategy;

@Entity
public class FormacionStrategyImpl extends FormacionStrategy{
	private static final long serialVersionUID = 1L;

	public FormacionStrategyImpl(List<Posicion> posiciones) {
		this.setPosiciones(posiciones);
	}
	
	public Jugador mejorJugador(Posicion posicion){
		Jugador jugadorMax = null;
		
		if(!this.getJugadores().isEmpty()){
			
			jugadorMax=this.getJugadores().get(0);
		
			for (Jugador jugador : this.getJugadores()) {
				if (jugador.getValorHabilidad(posicion) > jugadorMax.getValorHabilidad(posicion) ){
					jugadorMax=jugador;
				}
			}
			this.getJugadores().remove(jugadorMax);	
		}
		return jugadorMax;
	}

	protected List<Titular> armarTitulares(){
		List<Titular> titulares = new ArrayList<Titular>();
		for (Posicion posicion : this.getPosiciones()) {
			titulares.add(new Titular(this.mejorJugador(posicion), posicion));
		}
		return titulares;
	}
	
	@Override
	public Formacion armarFormacion(Equipo equipo) {
		this.setJugadores(equipo.getJugadores());
		Formacion formacion = new Formacion();
		formacion.setEquipo(equipo);
		formacion.setTitulares(this.armarTitulares());
		formacion.setSuplentes(this.getJugadores());
		
		return formacion;
	}

}
