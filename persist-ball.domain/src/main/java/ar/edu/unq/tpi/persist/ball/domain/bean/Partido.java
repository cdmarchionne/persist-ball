package ar.edu.unq.tpi.persist.ball.domain.bean;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Partido extends PersistentObject{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Equipo equipo1;
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Equipo equipo2;
	
	public abstract Equipo getGanador();

	public Equipo getEquipo1() {
		return equipo1;
	}

	public void setEquipo1(Equipo equipo1) {
		this.equipo1 = equipo1;
	}

	public Equipo getEquipo2() {
		return equipo2;
	}

	public void setEquipo2(Equipo equipo2) {
		this.equipo2 = equipo2;
	}
}
