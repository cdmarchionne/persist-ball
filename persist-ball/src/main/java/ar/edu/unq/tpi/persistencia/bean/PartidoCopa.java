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
        int golesEquipo1, golesEquipo2;

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
