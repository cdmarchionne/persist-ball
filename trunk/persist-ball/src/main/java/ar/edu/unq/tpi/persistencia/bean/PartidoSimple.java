package ar.edu.unq.tpi.persistencia.bean;

import javax.persistence.Basic;
import javax.persistence.Entity;

@Entity
public class PartidoSimple extends Partido {
    private static final long serialVersionUID = 1L;

    @Basic
    private int golesEquipo1, golesEquipo2;

    public PartidoSimple() {
    }

    public PartidoSimple(final Equipo equipo1, final Equipo equipo2) {
        super();
        this.setEquipo1(equipo1);
        this.setEquipo2(equipo2);
    }

    @Override
    public Equipo getGanador() {
        if (golesEquipo1 > golesEquipo2)
            return this.getEquipo1();
        else if (golesEquipo1 < golesEquipo2)
            return this.getEquipo2();
        return null;
    }

    public void simularPartido(final int golesEquipo1, final int golesEquipo2) {
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
    }

    public int getGolesEquipo1() {
        return golesEquipo1;
    }

    public void setGolesEquipo1(final int golesEquipo1) {
        this.golesEquipo1 = golesEquipo1;
    }

    public int getGolesEquipo2() {
        return golesEquipo2;
    }

    public void setGolesEquipo2(final int golesEquipo2) {
        this.golesEquipo2 = golesEquipo2;
    }

}
