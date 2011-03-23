package ar.edu.unq.tpi.persistencia.bean;

import ar.edu.unq.tpi.persistencia.logic.Formacion;

public class PartidoSimple extends Partido {
	private Formacion formacion1, formacion2;
	private int golesEquipo1, golesEquipo2;

	public PartidoSimple() {
	}

	public PartidoSimple(Formacion formacion1, Formacion formacion2,
			int golesEquipo1, int golesEquipo2) {
		super();
		this.formacion1 = formacion1;
		this.formacion2 = formacion2;
		this.golesEquipo1 = golesEquipo1;
		this.golesEquipo2 = golesEquipo2;
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

	public Formacion getFormacion1() {
		return formacion1;
	}

	public void setFormacion1(Formacion formacion1) {
		this.formacion1 = formacion1;
	}

	public Formacion getFormacion2() {
		return formacion2;
	}

	public void setFormacion2(Formacion formacion2) {
		this.formacion2 = formacion2;
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
