package ar.edu.unq.tpi.persist.ball.domain.logica;

import javax.persistence.Entity;

import ar.edu.unq.tpi.persist.ball.domain.bean.enums.Posicion;
import ar.edu.unq.tpi.persist.ball.domain.interfaces.Habilidad;


@Entity
public class HabilidadImpl extends Habilidad {
	private static final long serialVersionUID = 1L;

	public HabilidadImpl(){}
	
	public HabilidadImpl(Posicion posicion, int valor) {
		this.setPosicion(posicion);
		this.setValor(valor);
	}

	@Override
	public int getValor(Posicion posicion) {
	    if(this.getPosicion().equals(posicion)){
	        return getValor();
	    }
		return 0;
	}

}
