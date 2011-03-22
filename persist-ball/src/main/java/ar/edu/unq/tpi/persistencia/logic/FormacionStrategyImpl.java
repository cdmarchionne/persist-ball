package ar.edu.unq.tpi.persistencia.logic;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.tpi.persistencia.bean.Equipo;
import ar.edu.unq.tpi.persistencia.bean.Jugador;
import ar.edu.unq.tpi.persistencia.bean.Titular;
import ar.edu.unq.tpi.persistencia.enums.Posicion;
import ar.edu.unq.tpi.persistencia.interfaces.FormacionStrategy;

public class FormacionStrategyImpl implements FormacionStrategy{
	private List<Posicion> posiciones;
	private List<Jugador> jugadores;
	
	public FormacionStrategyImpl(List<Posicion> posiciones) {
		this.setPosiciones(posiciones);
	}
	
	public Jugador mejorJugador(Posicion posicion){
		Jugador jugadorMax = null;
		
		if(!this.jugadores.isEmpty()){
			
			jugadorMax=this.jugadores.get(0);
		
			for (Jugador jugador : this.jugadores) {
				if (jugador.getValorHabilidad(posicion) > jugadorMax.getValorHabilidad(posicion) ){
					jugadorMax=jugador;
				}
			}
			this.jugadores.remove(jugadorMax);	
		}
		return jugadorMax;
	}

	protected List<Titular> armarTitulares(){
		List<Titular> titulares = new ArrayList<Titular>();
		for (Posicion posicion : this.getPosiciones()) {
			titulares.add(new Titular(this.mejorJugador(posicion), posicion));
		}
		return  titulares;
	}
	
	@Override
	public Formacion armarFormacion(Equipo equipo) {
		this.jugadores = equipo.getJugadores();
		Formacion formacion = new Formacion();
		formacion.setEquipo(equipo);
		formacion.setTitulares(this.armarTitulares());
		formacion.setSuplentes(this.jugadores);
		
		return formacion;
	}

	public void setPosiciones(List<Posicion> posiciones) {
		this.posiciones = posiciones;
	}

	public List<Posicion> getPosiciones() {
		return posiciones;
	}



}
