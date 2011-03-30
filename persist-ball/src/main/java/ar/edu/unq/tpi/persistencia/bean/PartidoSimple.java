package ar.edu.unq.tpi.persistencia.bean;

import ar.edu.unq.tpi.persistencia.logic.Formacion;

public class PartidoSimple extends Partido {
	private int golesEquipo1, golesEquipo2;

	public PartidoSimple() {
	}

	public PartidoSimple(Equipo equipo1, Equipo equipo2) {
		super();
		this.setEquipo1(equipo1);
		this.setEquipo2(equipo2);
	}

	public Equipo getGanador(){
		if (golesEquipo1>golesEquipo2){
			return this.getEquipo1();
		}
		else if (golesEquipo1<golesEquipo2){
			return this.getEquipo2();
		}
		return null;
	}
	
	public void simularPartido(int golesEquipo1, int golesEquipo2) {
		this.golesEquipo1 = golesEquipo1;
		this.golesEquipo2 = golesEquipo2;
	}

	public int getGolesEquipo1() {
		return golesEquipo1;
	}

	public void setGolesEquipo1(int golesEquipo1) {
		this.golesEquipo1 = golesEquipo1;
	}

	public int getGolesEquipo2() {
		return golesEquipo2;
	}

	public void setGolesEquipo2(int golesEquipo2) {
		this.golesEquipo2 = golesEquipo2;
	}

}
