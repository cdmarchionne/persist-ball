package ar.edu.unq.tpi.persistencia.bean;

public abstract class Partido {

	private Equipo equipo1,equipo2;
	
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
