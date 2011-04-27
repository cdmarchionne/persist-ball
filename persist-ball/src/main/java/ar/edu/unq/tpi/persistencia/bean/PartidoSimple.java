package ar.edu.unq.tpi.persistencia.bean;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ar.edu.unq.tpi.persistencia.exception.UserException;

@Entity
public class PartidoSimple extends Partido {
    private static final long serialVersionUID = 1L;

    @Basic
    private int golesEquipo1, golesEquipo2;

    @Temporal(value = TemporalType.DATE)
    private Date fecha;

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

    public void simularPartido(final int golesEquipo1, final int golesEquipo2, final Date date) {
        this.golesEquipo1 = golesEquipo1;
        this.golesEquipo2 = golesEquipo2;
        this.fecha = date;
    }

    public int getGolesEquipo(final Equipo equipo) {
        if (equipo.equals(this.getEquipo1()))
            return this.getGolesEquipo1();
        else if (equipo.equals(this.getEquipo2()))
            return this.getGolesEquipo2();
        throw new UserException("No se encontro el equipo buscado");
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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date date) {
		this.fecha = date;
	}

}
