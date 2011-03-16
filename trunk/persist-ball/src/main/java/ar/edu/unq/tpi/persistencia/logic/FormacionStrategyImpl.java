package ar.edu.unq.tpi.persistencia.logic;

import java.util.List;

import javax.swing.text.Position;

import ar.edu.unq.tpi.persistencia.bean.Equipo;
import ar.edu.unq.tpi.persistencia.bean.Jugador;
import ar.edu.unq.tpi.persistencia.enums.Posicion;
import ar.edu.unq.tpi.persistencia.interfaces.FormacionStrategy;

public class FormacionStrategyImpl implements FormacionStrategy{
	private List<Posicion> posicion;
	private List<Jugador> jugadores;
	
	public Jugador mejorJugador(Posicion posicion){
		Jugador jugadorMax = null;
		
		if(!this.jugadores.isEmpty()){
			
			jugadorMax=this.jugadores.get(0);
		
			for (Jugador jugador : this.jugadores) {
				if (jugador.getValor(posicion) > jugadorMax.getValor(posicion) ){
					jugadorMax=jugador;
				}
			}
			this.jugadores.remove(jugadorMax);	
		}
		return jugadorMax;
	}

	protected void crearPosEquipoTitular(){
		this.getPosicion().add(Posicion.ARQUERO);
		this.getPosicion().add(Posicion.VOLANTE_DEFENCIVO);
		this.getPosicion().add(Posicion.VOLANTE_DEFENCIVO);
		this.getPosicion().add(Posicion.MEDIA_PUNTA);
		this.getPosicion().add(Posicion.MEDIA_PUNTA);
		this.getPosicion().add(Posicion.CENTRAL);
		this.getPosicion().add(Posicion.DELANTERO);
		this.getPosicion().add(Posicion.DELANTERO);
		this.getPosicion().add(Posicion.ENGANCHE);
		this.getPosicion().add(Posicion.VOLANTE_LATERAL);
		this.getPosicion().add(Posicion.VOLANTE_LATERAL);
	}
	
	@Override
	public Formacion armarFormacion(Equipo equipo) {
		return null;
	}

	public void setPosicion(List<Posicion> posicion) {
		this.posicion = posicion;
	}

	public List<Posicion> getPosicion() {
		return posicion;
	}


}
