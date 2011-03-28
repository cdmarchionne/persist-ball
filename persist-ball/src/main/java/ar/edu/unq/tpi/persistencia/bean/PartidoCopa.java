package ar.edu.unq.tpi.persistencia.bean;

public class PartidoCopa extends Partido {
    private PartidoSimple partidoIda, partidoVuelta;

    private int golesPenalesEquipo1, golesPenalesEquipo2;

    public PartidoCopa() {
    }

    public PartidoCopa(final PartidoSimple partidoIda, final PartidoSimple partidoVuelta,
            final int golesPenalesEquipo1, final int golesPenalesEquipo2) {
        super();
        this.partidoIda = partidoIda;
        this.partidoVuelta = partidoVuelta;
        this.golesPenalesEquipo1 = golesPenalesEquipo1;
        this.golesPenalesEquipo2 = golesPenalesEquipo2;
    }

    @Override
    public Equipo getGanador() {
        private int golesEquipo1, golesEquipo2;

        golesEquipo1 = partidoIda.getGolesEquipo1() + partidoVuelta.getGolesEquipo1();
        golesEquipo2 = partidoIda.getGolesEquipo2() + partidoVuelta.getGolesEquipo2();

        if (golesEquipo1 > golesEquipo2)
            return this.getEquipo1();
        else if (golesEquipo1 < golesEquipo2)
            return this.getEquipo2();
        else if (golesPenalesEquipo1 > golesPenalesEquipo2)
            return this.getEquipo1();
        else if (golesPenalesEquipo1 < golesPenalesEquipo2)
            return this.getEquipo2();

        return null;
        // Equipo ganadorIda,ganadorVuelta;
        // int puntajeEquipo1=0;
        //
        // ganadorIda= this.partidoIda.getGanador();
        // ganadorVuelta= this.partidoVuelta.getGanador();
        //
        // if (ganadorIda==this.getEquipo1()){
        // puntajeEquipo1+=2;
        // }
        // else if (ganadorIda==null){
        // puntajeEquipo1+=1;
        // }
        //
        // if (ganadorVuelta==this.getEquipo1()){
        // puntajeEquipo1+=2;
        // }
        // else if (ganadorVuelta==null){
        // puntajeEquipo1+=1;
        // }
        //
        //
        //
        //
        // golesEquipo1=getPartidoIda().getGolesEquipo1()
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

}
