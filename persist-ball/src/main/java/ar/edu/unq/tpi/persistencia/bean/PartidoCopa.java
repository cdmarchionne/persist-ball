package ar.edu.unq.tpi.persistencia.bean;

public class PartidoCopa extends Partido {
	private PartidoSimple partidoIda, partidoVuelta;
	private int golesPenalesEquipo1, golesPenalesEquipo2;

	public PartidoCopa() {
	}

	public PartidoCopa(PartidoSimple partidoIda, PartidoSimple partidoVuelta,
			int golesPenalesEquipo1, int golesPenalesEquipo2) {
		super();
		this.partidoIda = partidoIda;
		this.partidoVuelta = partidoVuelta;
		this.golesPenalesEquipo1 = golesPenalesEquipo1;
		this.golesPenalesEquipo2 = golesPenalesEquipo2;
	}

	public Equipo getGanador(){
		return null;
	}

	public PartidoSimple getPartidoIda() {
		return partidoIda;
	}

	public void setPartidoIda(PartidoSimple partidoIda) {
		this.partidoIda = partidoIda;
	}

	public PartidoSimple getPartidoVuelta() {
		return partidoVuelta;
	}

	public void setPartidoVuelta(PartidoSimple partidoVuelta) {
		this.partidoVuelta = partidoVuelta;
	}

	public int getGolesPenalesEquipo1() {
		return golesPenalesEquipo1;
	}

	public void setGolesPenalesEquipo1(int golesPenalesEquipo1) {
		this.golesPenalesEquipo1 = golesPenalesEquipo1;
	}

	public int getGolesPenalesEquipo2() {
		return golesPenalesEquipo2;
	}

	public void setGolesPenalesEquipo2(int golesPenalesEquipo2) {
		this.golesPenalesEquipo2 = golesPenalesEquipo2;
	}

}
