package ar.edu.unq.tpi.persist.ball.domain.bean;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class PartidoCopa extends Partido {
    private static final long serialVersionUID = 1L;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private PartidoSimple partidoIda;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private PartidoSimple partidoVuelta;

    @Basic
    private int golesPenalesEquipo1, golesPenalesEquipo2;

    public PartidoCopa() {
    }

    public PartidoCopa(final Equipo equipo1, final Equipo equipo2) {
        super();
        this.setEquipo1(equipo1);
        this.setEquipo2(equipo2);
    }

    @Override
    public Equipo getGanador() {

        if (this.golesEquipo1() == this.golesEquipo2()) {
            if (golesPenalesEquipo1 > golesPenalesEquipo2)
                return this.getEquipo1();
            else
                return this.getEquipo2();
        } else if (this.golesEquipo1() > this.golesEquipo2())
            return this.getEquipo1();
        else
            return this.getEquipo2();
    }

    public void simularPartido(final PartidoSimple partidoSimple1, final PartidoSimple partidoSimple2,
            final int golesPenales1, final int golesPenales2) {
        partidoIda = partidoSimple1;
        partidoVuelta = partidoSimple2;
        golesPenalesEquipo1 = golesPenales1;
        golesPenalesEquipo2 = golesPenales2;
    }

    protected int golesEquipo(final Equipo equipo) {
        return partidoIda.getGolesEquipo(equipo) + partidoVuelta.getGolesEquipo(equipo);
    }

    protected int golesEquipo1() {
        return this.golesEquipo(this.getEquipo1());
    }

    protected int golesEquipo2() {
        return this.golesEquipo(this.getEquipo2());
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

    public void simularPartido(final PartidoSimple partidoSimple1, final PartidoSimple partidoSimple2) {
        this.simularPartido(partidoSimple1, partidoSimple2, 0, 0);

    }

}
