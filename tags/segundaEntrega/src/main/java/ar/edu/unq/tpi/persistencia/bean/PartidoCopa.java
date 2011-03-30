package ar.edu.unq.tpi.persistencia.bean;

public class PartidoCopa extends Partido {
    private PartidoSimple partidoIda, partidoVuelta;

    private int golesPenalesEquipo1, golesPenalesEquipo2;

    public PartidoCopa() {
    }

    public PartidoCopa(Equipo equipo1, Equipo equipo2) {
        super();
        this.setEquipo1(equipo1);
        this.setEquipo2(equipo2);
    }

    @Override
    public Equipo getGanador() {

        if(golesEquipo1() == golesEquipo2()){
	        if (golesPenalesEquipo1 > golesPenalesEquipo2)
	        	return this.getEquipo1();
	        else
	        	return this.getEquipo2();
        }else if (golesEquipo1() > golesEquipo2())
            return this.getEquipo1();
        else         
            return this.getEquipo2();
    }
    
    public void simularPartido(PartidoSimple partidoSimple1, PartidoSimple partidoSimple2, int golesPenales1, int golesPenales2){
    	this.partidoIda = partidoSimple1;
    	this.partidoVuelta = partidoSimple2;
    	this.golesPenalesEquipo1 = golesPenales1;
    	this.golesPenalesEquipo2 = golesPenales2;
    }
    

	protected int golesEquipo2() {
		return partidoIda.getGolesEquipo2() + partidoVuelta.getGolesEquipo2();
	}

	protected int golesEquipo1() {
		return partidoIda.getGolesEquipo1() + partidoVuelta.getGolesEquipo1();
	}

    public PartidoSimple getPartidoIda() {
        return partidoIda;
    }

    public void setPartidoIda(final PartidoSimple partidoIda) {
        this.partidoIda = partidoIda;
    }

    public PartidoSimple getPartidoVuelta() {
        return partidoVuelta;
    }

    public void setPartidoVuelta(final PartidoSimple partidoVuelta) {
        this.partidoVuelta = partidoVuelta;
    }

    public int getGolesPenalesEquipo1() {
        return golesPenalesEquipo1;
    }

    public void setGolesPenalesEquipo1(final int golesPenalesEquipo1) {
        this.golesPenalesEquipo1 = golesPenalesEquipo1;
    }

    public int getGolesPenalesEquipo2() {
        return golesPenalesEquipo2;
    }

    public void setGolesPenalesEquipo2(final int golesPenalesEquipo2) {
        this.golesPenalesEquipo2 = golesPenalesEquipo2;
    }

	public void simularPartido(PartidoSimple partidoSimple1, PartidoSimple partidoSimple2) {
		this.simularPartido(partidoSimple1, partidoSimple2, 0, 0);
		
	}

}
